package com.atkexin.ssyx.product.controller;

import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.product.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "文件上传接口")
@RestController
@RequestMapping(value="admin/product")
@CrossOrigin
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @ApiOperation("图片上传")
    @PostMapping("fileUpload")
    public Result Upload(MultipartFile file) throws Exception{
        //得到图片
        String url=fileUploadService.upload(file);
        return  Result.ok(url);

    }

}
