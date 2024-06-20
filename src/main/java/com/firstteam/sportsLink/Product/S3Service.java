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

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        try {
            InputStream inputStream = file.getInputStream();
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));
            return fileName;
        } catch (IOException e) {
            // 파일의 InputStream을 가져오는 중에 발생하는 예외 처리
            e.printStackTrace();
            // 추가적인 예외 처리 로직을 추가할 수 있습니다.
            return null;
        } catch (AmazonServiceException e) {
            // Amazon S3 서비스에서 발생하는 예외 처리
            e.printStackTrace();
            // S3 서비스의 오류 상태 코드와 메시지를 출력할 수 있습니다.
            System.err.println("AmazonServiceException: " + e.getMessage());
            return null;
        } catch (SdkClientException e) {
            // AWS SDK 클라이언트에서 발생하는 예외 처리
            e.printStackTrace();
            // AWS SDK 클라이언트의 오류 상태 코드와 메시지를 출력할 수 있습니다.
            System.err.println("SdkClientException: " + e.getMessage());
            return null;
        }
    }



}
