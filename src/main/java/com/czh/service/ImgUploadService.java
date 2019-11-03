package com.czh.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImgUploadService {

    String uploadImage(MultipartFile file, Integer uid,int type);
}
