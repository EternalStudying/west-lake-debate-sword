package com.swyxl.common.service;

import org.springframework.web.multipart.MultipartFile;


public interface MinioService {
    String upload(MultipartFile file, String type);

    String download(String downloadUrl);
}
