package com.oreki5.keionbu.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dbEntities.FileMetaData;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public void uploadFile(MultipartFile file, FileMetaData metadata)
            throws IOException {

        s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(metadata.storedName())
                .build(), RequestBody.fromBytes(file.getBytes()));

    }

    public byte[] downloadFile(FileMetaData metaData) {
        ResponseBytes<GetObjectResponse> objectAsBytes = s3Client.getObjectAsBytes(GetObjectRequest.builder()
                .bucket(bucketName)
                .key(metaData.storedName())
                .build());

        return objectAsBytes.asByteArray();
    }
}
