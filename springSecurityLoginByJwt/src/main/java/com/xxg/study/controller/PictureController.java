package com.xxg.study.controller;


import com.xxg.study.common.AjaxResult;
import com.xxg.study.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("file")
@Slf4j
public class PictureController {


    @PostMapping("/image/upload")
    public AjaxResult uploadImage(@RequestParam("file")MultipartFile file){
        String path="F:/upload/";

        String fileName = file.getOriginalFilename();
        log.info("upload image"+fileName);
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        Assert.isTrue(!"jpg".equals(suffix) || !"jpeg".equals(suffix),"文件格式不存在！！！");
        String newFileName= UUID.randomUUID().toString();
        String upload = FileUtil.upload(file, path, newFileName);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode("200");
        ajaxResult.setMessage("上传成功");
        ajaxResult.setData("文件名为："+newFileName);
        return ajaxResult;
    }
}
