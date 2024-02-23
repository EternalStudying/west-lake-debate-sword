package com.swyxl.common.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface ImageUploadService {
    String upload(MultipartFile image, String type);
}
