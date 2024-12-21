package com.example.crucialfunctiontest.controller;

import com.example.crucialfunctiontest.result.R;
import com.example.crucialfunctiontest.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xushupeng
 * @Date 2024-07-16 23:07
 */
@CrossOrigin
@RestController
public class MinioController {
    @GetMapping("/test/{username}")
    public String myTest(@PathVariable String username) {
        return "Hello,world! Hello " + username;
    }

    @Autowired
    private MinioUtils minioUtils;
    @Value("${minio.endpoint}")
    private String address;
    @Value("${minio.bucketName}")
    private String bucketName;

    @PostMapping("/upload")
    public Object upload(MultipartFile[] files) {
        System.out.println("bucketName is " + bucketName);
        List<String> uploads = minioUtils.upload(files);
        for (String it : uploads) {
            System.out.println("it is " + it);
        }
        return new R(true, 200, uploads.get(0));
    }
    /*
     *
     * http://localhost:8099/download/8693941_2411b35cd.jpg
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) {
        return minioUtils.download(fileName);
    }
    //http://localhost:8099/getBucketObjects/mystorge
    @GetMapping("/getBucketObjects/{bucketName}")
    public Object getBucketObjects(@PathVariable("bucketName") String bucketName) {
       return minioUtils.listObjects(bucketName);
    }

    @GetMapping("/deleteObject/{fileName}")
    public Object deleteObject(@PathVariable("fileName") String fileName) {
        System.out.println("删除的fileName is "+fileName);
        boolean flag=minioUtils.deleteObject(bucketName,fileName);
        return new R(true, 200,flag);
    }
}
