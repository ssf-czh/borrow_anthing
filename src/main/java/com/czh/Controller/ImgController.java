package com.czh.Controller;

import com.czh.common.utils.JWTUtil;
import com.czh.common.vo.Result;
import com.czh.pojo.URL;
import com.czh.service.ImgUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/img")
public class ImgController {

    @Autowired
    private ImgUploadService imgUploadService;

    @PostMapping("/upload")
    public ResponseEntity<Result> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest
                    ,@RequestParam(value = "type",required = true) int type){
//        type 1表示用户头像 2表示物品照片 3表示帖子图片
        String token = httpServletRequest.getHeader("XW-Token");
        Integer uid = JWTUtil.parseToUid(token);


        String url = imgUploadService.uploadImage(file,uid,type);
        Result result = new Result(200, "成功", new URL(url));
        return ResponseEntity.ok(result);
    }

//    @GetMapping(value = "/get", produces = MediaType.IMAGE_JPEG_VALUE)
//    public BufferedImage getImage(String url) throws IOException {
//        System.out.println(url);
//        FileInputStream fis = new FileInputStream(new File(url));
//        System.out.println("9999");
//        BufferedImage read = ImageIO.read(fis);
//        System.out.println(read);
//        return read;
//    }
        @RequestMapping(value = "/get",produces = MediaType.IMAGE_JPEG_VALUE)
        public byte[] getImage(@RequestParam(value = "url",required = true) String url) throws IOException {
            File file = new File(url);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }


}
