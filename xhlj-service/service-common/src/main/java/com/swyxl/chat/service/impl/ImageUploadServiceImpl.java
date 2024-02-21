package com.swyxl.chat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.swyxl.chat.properties.MinioProperty;
import com.swyxl.chat.service.ImageUploadService;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.vo.common.ResultCodeEnum;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    private MinioProperty minioProperty;

    @Override
    public String upload(MultipartFile image, String type) {
        try {
            // 创建MinioClient对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperty.getEndpointUrl())
                            .credentials(minioProperty.getAccessKey(), minioProperty.getSecretKey())
                            .build();

            // 创建bucket
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperty.getBucketName()).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperty.getBucketName()).build());
            } else {
                System.out.println("Bucket 'xhlj' already exists.");
            }

            //获取文件名称
            //1. 每个上传文件的名称唯一的  uuid生成   01.jpg
            //2. 根据当前日期对上传文件进行分组  20230910

            //20230910/01.jpg
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            String suffix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.'));

            String filename = "service/" + dateDir + "/" + type + "/" + uuid + suffix;
            //文件上传
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperty.getBucketName())
                            .object(filename)
                            .stream(image.getInputStream(), image.getSize(), -1)
                            .build()
            );

            //获取上传文件在minio路径
            return minioProperty.getEndpointUrl() + "/" + minioProperty.getBucketName()+ "/" + filename;
        }catch (Exception e){
            e.printStackTrace();
            throw new XHLJException(ResultCodeEnum.SYSTEM_ERROR);
        }

    }
}
