package com.cc.pic.api.src.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName MyTest
 * @FileName FileListController
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fileList")
@Api(tags = "文件统一管理库，如果是为了速度，可使用redis，但是总感觉redis不太可靠")
public class FileListController {

}
