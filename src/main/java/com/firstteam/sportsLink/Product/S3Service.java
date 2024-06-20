package com.firstteam.sportsLink.Product;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;

    private String bucketName = "sportlink-image";

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = generateFileName(file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            InputStream inputStream = file.getInputStream();
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            return "Amazon S3 service exception: " + e.getMessage();
        } catch (SdkClientException e) {
            e.printStackTrace();
            return "AWS SDK client exception: " + e.getMessage();
        }
    }

    private String generateFileName(MultipartFile file) {
        return file.getOriginalFilename();
    }



}
