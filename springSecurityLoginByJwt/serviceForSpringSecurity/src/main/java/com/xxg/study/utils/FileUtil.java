package com.xxg.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Slf4j
public class FileUtil {


    public static String upload(MultipartFile file, String path, String fileName) {
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println(filePath + "目录不存在，需要创建");
            //创建目录
            filePath.mkdir();
        }
        String fileFullName=new StringBuilder().append(filePath).append(File.separator).append(fileName).toString();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileFullName);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            log.info("文件上传完成！！！");
        }
        return fileFullName;
    }
}
