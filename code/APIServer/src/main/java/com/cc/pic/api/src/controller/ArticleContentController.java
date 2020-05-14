package com.cc.pic.api.src.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName PhotographyExhibition
 * @FileName ArticleContentController
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/articleContent")
@Api(tags = "文章内容表 图文，对应文章id，一个文章只有一个内容")
public class ArticleContentController {

}
