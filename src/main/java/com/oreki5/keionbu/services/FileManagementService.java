package com.oreki5.keionbu.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.management.InvalidAttributeValueException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dbEntities.FileMetaData;
import com.oreki5.keionbu.repositories.AssignmentsRepo;
import com.oreki5.keionbu.repositories.FileMetaDataRepo;
import com.oreki5.keionbu.repositories.LessonsRepo;
import com.oreki5.keionbu.utils.StorageDirEnum;

@Service
public class FileManagementService {
    @Autowired
    private FileMetaDataRepo metadataRepo;

    @Autowired
    private LessonsRepo lessonsRepo;

    @Autowired
    private AssignmentsRepo assignmentsRepo;

    @Autowired
    private S3Service s3Service;

    // private final String STORAGE_DIR = "C:\\Users\\adity\\Desktop\\Oreki\\CS
    // Related Projects\\StorageDirForProjects\\keionbu";
    private final List<String> allowedExtensionsForLesson = Arrays.asList("png",
            "jpeg", "jpg", "pdf");
    private final List<String> allowedExtensionsForSubmission = Arrays.asList("mp3", "flac", "aac");

    @Transactional
    public FileMetaData uploadFile(MultipartFile file, StorageDirEnum subDir, String userId)
            throws InvalidAttributeValueException, IOException, IllegalArgumentException {
        if (file == null) {
            return null;
        }

        if (subDir == StorageDirEnum.LESSON &&
                !allowedExtensionsForLesson.contains(getFileExtension(file.getOriginalFilename()))) {
            throw new IllegalArgumentException("This file type is allowed for lessons");
        }

        if (subDir == StorageDirEnum.SUBMISSION &&
                !allowedExtensionsForSubmission.contains(getFileExtension(file.getOriginalFilename()))) {
            throw new IllegalArgumentException("This file type is allowed for submission");
        }

        String storingName = subDir.getPath() + File.separator + UUID.randomUUID().toString() + "."
                + getFileExtension(file.getOriginalFilename());

        // String storingName = UUID.randomUUID().toString();
        // var fileToUpload = new File(STORAGE_DIR + subDir.getPath() + File.separator +
        // storingName + "."
        // + getFileExtension(file.getOriginalFilename()));

        // String f = fileToUpload.getAbsoluteFile().toString();
        // String f2 = Paths.get(STORAGE_DIR + subDir.getPath()).toString();
        // boolean b = f.startsWith(f2);
        // if (!fileToUpload.getAbsoluteFile().toString()
        // .startsWith(Paths.get(STORAGE_DIR + subDir.getPath()).toString())) {
        // throw new InvalidAttributeValueException("Invalid file name");

        // }

        // Files.copy(file.getInputStream(), fileToUpload.toPath(),
        // StandardCopyOption.REPLACE_EXISTING);

        FileMetaData metadata = new FileMetaData(storingName, file.getOriginalFilename(), userId,
                getFileExtension(file.getOriginalFilename()), file.getSize(), Instant.now(), ObjectId.get());

        s3Service.uploadFile(file, metadata);

        return metadataRepo.save(metadata);
    }

    public byte[] downloadFile(FileMetaData metadata) throws FileNotFoundException {

        return s3Service.downloadFile(metadata);

        // Path filePath = Paths.get(STORAGE_DIR + subDir.getPath());

        // filePath =
        // filePath.resolve(metadata.originalName()).normalize().toAbsolutePath();

        // Path rootPath = Paths.get(STORAGE_DIR +
        // subDir.getPath()).normalize().toAbsolutePath();

        // if (!filePath.startsWith(rootPath)) {
        // throw new SecurityException("security risk");
        // }

        // var file = new File(filePath.toString());

        // if (!file.exists()) {
        // throw new FileNotFoundException("file not found");
        // }
        // return file;
    }

    public void deleteFile(FileMetaData metadata) {
        metadataRepo.deleteById(metadata.id());
    }

    public String getFileExtension(String filename) {

        return filename.lastIndexOf('.') == -1 ? "" : filename.substring(filename.lastIndexOf('.') + 1);

    }

    public ResponseEntity<byte[]> getAssignmentLessonFile(String id) throws FileNotFoundException {
        FileMetaData metaData = assignmentsRepo.findById(id).orElseThrow().getLesson().getLessonFile();
        byte[] fileBody = downloadFile(metaData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + metaData.originalName() + "\"")
                .body(fileBody);

    }

    public ResponseEntity<byte[]> getSubmmissionFile(String id) throws FileNotFoundException {

        FileMetaData metaData = assignmentsRepo.findById(id).orElseThrow().getSubmission();
        byte[] fileBody = downloadFile(metaData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + metaData.originalName() + "\"")
                .body(fileBody);
    }

}
