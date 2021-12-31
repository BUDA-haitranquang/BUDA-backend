package com.higroup.Buda.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    /**
     * Upload a file to Amazon S3 bucket, return the URL to the uploaded object
     * @param file the object to be uploaded to Amazon S3 bucket
     * @return the URL to the uploaded object on Amazon S3 bucket
     * @throws AmazonServiceException if the call was transmitted successfully, but Amazon S3 couldn't process
     * @throws SdkClientException if Amazon S3 couldn't be contacted for a response, or the client couldn't parse the response from Amazon S3.
     */
    public String uploadFile(MultipartFile file) {
        try {
            File fileObj = convertMultiPartFileToFile(file);
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            this.grantObjectPermission(fileName, GroupGrantee.AllUsers, Permission.Read);
            fileObj.delete();
            return this.getResourceUrl(fileName);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Amazon S3 couldn't process the request");
        } catch (SdkClientException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Cannot connect to Amazon S3");
        }
    }

    private void grantObjectPermission(String fileName, Grantee grantee, Permission permission) {
        AccessControlList acl = s3Client.getObjectAcl(bucketName, fileName);
        acl.grantPermission(grantee, permission);
        s3Client.setObjectAcl(bucketName, fileName, acl);
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete an object on Amazon S3 bucket
     * @param fileName name of the file to be removed
     * @return confirmation message
     */
    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    /**
     * Get the path to resource
     * @param itemName name of the object to be searched for path
     * @return the path to the item
     */
    public String getResourceUrl(String itemName) {
        return s3Client.getUrl(bucketName, itemName).toString();
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
