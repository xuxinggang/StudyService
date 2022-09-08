package com.xxg.study.controller;

import com.xxg.study.utils.QRCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class getWebQRController {

    @GetMapping("/getQRCode")
    public String getWebQR() {
        String url = "www.baidu.com";
        BufferedImage image = QRCodeUtil.createImage("utf-8", url, 300, 300);
        QRCodeUtil.addUpFont(image, "企业信息填写");
        String imagePath = null;
        String path = null;
        try {
            File file = new File("D://meeting");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            ImageIO.write(image, "JPEG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }
}
