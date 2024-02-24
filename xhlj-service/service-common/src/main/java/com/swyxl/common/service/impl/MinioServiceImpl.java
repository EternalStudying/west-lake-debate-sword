package com.swyxl.common.service.impl;

import cn.hutool.core.date.DateUtil;
import com.swyxl.common.properties.MinioProperty;
import com.swyxl.common.service.MinioService;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.model.constant.ViewContentType;
import com.swyxl.model.vo.common.ResultCodeEnum;
import io.minio.*;
import io.minio.http.Method;
import jakarta.servlet.ServletOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MinioServiceImpl implements MinioService {

    @Autowired
    private MinioProperty minioProperty;
    @Autowired
    private MinioClient minioClient;

    @Override
    public String upload(MultipartFile image, String type) {
        try {
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

            String contentType = suffix.substring(1);

            String filename = "service/" + dateDir + "/" + type + "/" + uuid + suffix;
            //文件上传
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperty.getBucketName())
                            .object(filename)
                            .contentType(ViewContentType.getContentType(contentType))
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

    @Override
    public String download(String downloadUrl) {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder().bucket(minioProperty.getBucketName()).object(downloadUrl).build()
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new XHLJException(ResultCodeEnum.FILE_NOT_EXIST);
        }

        try {
            Map<String,String> headers = new HashMap<>();
            headers.put("response-content-type", "application/octet-stream");

            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minioProperty.getBucketName())
                            .object(downloadUrl)
                            .expiry(60 * 60 * 24)
                            .extraQueryParams(headers)
                            .build());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
