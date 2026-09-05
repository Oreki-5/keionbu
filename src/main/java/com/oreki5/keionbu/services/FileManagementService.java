package com.oreki5.keionbu.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import javax.management.InvalidAttributeValueException;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dbEntities.FileMetaData;
import com.oreki5.keionbu.utils.StorageDirEnum;

@Service
public class FileManagementService {

    private final String STORAGE_DIR = "";

    public FileMetaData uploadFile(MultipartFile file, StorageDirEnum subDir, String userId)
            throws InvalidAttributeValueException, IOException {
        if (file == null) {
            return null;
        }
        String storingName = UUID.randomUUID().toString();
        var fileToUpload = new File(STORAGE_DIR + subDir.getPath() + File.separator + file.getOriginalFilename());

        if (Objects.equals(fileToUpload.getParent(), STORAGE_DIR + subDir.getPath())) {
            throw new InvalidAttributeValueException("Invalid file name");

        }

        Files.copy(file.getInputStream(), fileToUpload.toPath(), StandardCopyOption.REPLACE_EXISTING);

        FileMetaData metadata = new FileMetaData(file.getOriginalFilename(), userId,
                getFileExtension(file.getOriginalFilename()), file.getSize(), Instant.now(), ObjectId.get());
        return null;
    }

    public File downloadFile(StorageDirEnum subDir, FileMetaData metadata) throws FileNotFoundException {

        Path filePath = Paths.get(STORAGE_DIR + subDir.getPath());

        filePath = filePath.resolve(metadata.originalName()).normalize().toAbsolutePath();

        Path rootPath = Paths.get(STORAGE_DIR + subDir.getPath()).normalize().toAbsolutePath();

        if (!filePath.startsWith(rootPath)) {
            throw new SecurityException("security risk");
        }

        var file = new File(filePath.toString());

        if (!file.exists()) {
            throw new FileNotFoundException("file not found");
        }
        return file;
    }

    public void deleteFile(FileMetaData metadata) {

    }

    public String getFileExtension(String filename) {

        return filename.lastIndexOf('.') == -1 ? "" : filename.substring(filename.lastIndexOf('.') + 1);

    }
}
