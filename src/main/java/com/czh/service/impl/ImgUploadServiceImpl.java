package com.czh.service.impl;

import com.czh.JieBeiSpringApplication;
import com.czh.common.enums.ExceptionEnum;
import com.czh.common.exception.JieBeiException;
import com.czh.service.ImgUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ImgUploadServiceImpl implements ImgUploadService {
    private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg","image/png","image/bmp");

    public String uploadImage(MultipartFile file, Integer uid, int type) {
        try {
//        检查是否为image
            if(!ALLOW_TYPES.contains(file.getContentType())){
                throw new JieBeiException(ExceptionEnum.INVAILD_FILE_TYPE);
            }
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(bufferedImage == null){
                throw new JieBeiException(ExceptionEnum.INVAILD_FILE_TYPE);
            }



            //        准备目标路径
//            String url = JieBeiSpringApplication.class.getClassLoader().getResource("").getPath()+file.getOriginalFilename();

            String url = JieBeiSpringApplication.class.getClassLoader().getResource("").getPath()+uid+System.currentTimeMillis()+type+"."+file.getContentType().split("/")[1];



            File dest = new File(url);
            //保存文件到本地

            file.transferTo(dest);
            //返回路径

            return url;
        } catch (IOException e) {
            throw new JieBeiException(ExceptionEnum.FAIL_UPLOAD_IMAGE);
        }
    }
}
