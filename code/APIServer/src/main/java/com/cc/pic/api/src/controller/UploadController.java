package com.cc.pic.api.src.controller;

import cn.hutool.core.date.DateUtil;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.config.SrcConfig;
import com.cc.pic.api.utils.IdBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Iterator;

/**
 * @ProJectName API
 * @FileName UploadController
 * @Description
 * @Author CandyMuj
 * @Date 2020/4/9 22:28
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fileserver")
@Api(tags = "文件服务")
public class UploadController {


    @ApiOperation("单文件上传-上传文件、图片")
    @PostMapping("/upload")
    public Result<String> upload(
            @ApiIgnore HttpServletRequest request
    ) {
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            if (multiRequest != null) {
                Iterator<String> iter = multiRequest.getFileNames();
                log.info("iter.hasNext: {}", iter.hasNext());

                if (iter.hasNext()) {
                    MultipartFile multipartFile = multiRequest.getFile(iter.next());

                    if (multipartFile != null) {
                        String fileName = multipartFile.getOriginalFilename();
                        String contentType = multipartFile.getContentType().toLowerCase();
                        log.info("upload filename: {}", fileName);
                        log.info("upload contentType: {}", contentType);

                        String savedName = getFileName(fileName);
                        String savedFilePath = SrcConfig.PATH.concat(savedName);
                        log.info("saved filepath: {}", savedFilePath);


                        File diskFile = new File(savedFilePath);
                        if (!diskFile.exists()) {
                            diskFile.mkdirs();
                        }
                        multipartFile.transferTo(diskFile);

                        return new Result<>(savedName);
                    }
                }
            }
        } catch (Exception e) {
            log.error("文件上传异常", e);
        }

        return Result.Error("Upload failure");
    }

    private String getFileName(String fileName) throws Exception {
        StringBuilder sbuild = new StringBuilder();
        sbuild.append(File.separator);
        sbuild.append(DateUtil.format(new Date(), "yyMM")).append("00").append(DateUtil.thisWeekOfMonth());
        sbuild.append(File.separator);
        sbuild.append(IdBuilder.genRandomStr(false, 10));
        sbuild.append(fileName, fileName.lastIndexOf("."), fileName.length());

        return sbuild.toString();
    }

}
