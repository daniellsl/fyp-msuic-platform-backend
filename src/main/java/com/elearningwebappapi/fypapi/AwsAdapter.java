package com.elearningwebappapi.fypapi;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import java.io.IOException;
import java.lang.RuntimeException;
import java.net.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;

@Component
public class AwsAdapter {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private  AmazonS3 amazonS3Client;

    public URL storeObjectInS3(MultipartFile file, String fileName, String contentType) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(file.getSize());
        
        try {
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName,file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch(AmazonClientException | IOException exception) {
            // handle err
            throw new RuntimeException("Error while uploading file.");
        }

        return amazonS3Client.getUrl(bucketName, fileName);
    }

    public S3Object fetchObject(String awsFileName) {

        S3Object s3Object;

        try {
            s3Object = amazonS3Client.getObject(new GetObjectRequest(bucketName, awsFileName));
        } catch (AmazonServiceException serviceException) {
            // handle err
            throw new RuntimeException("Error while streaming File.");
        } catch (AmazonClientException exception) {
            throw new RuntimeException("Error while streaming File.");
        }

        return s3Object;
    }

    // public void deleteObject(String key) {
    //     try {
    //         amazonS3Client.deleteObject(bucketName, key);
    //     } catch (Exception e) {
    //         // handle err
    //         throw new Exception(e);
    //     }
    // }
}