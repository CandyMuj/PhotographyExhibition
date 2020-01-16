package com.cc.pic.api.src.controller;

import com.cc.pic.api.annotations.Ann;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.src.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @ProJectName APIServer
 * @FileName ArticleController
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/15 14:58
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/article")
@Api(tags = "文章管理")
public class ArticleController {
    @Resource
    private IArticleService articleService;


    @Ann
    @ApiOperation("添加或修改文章")
    @PostMapping("/addOrUpd")
    public Result addOrUpd(
            @ApiParam(required = false, name = "文章id 如果有值则表示更新") Integer articleId,
            @ApiParam(required = true, name = "文章标题") String title,
            @ApiParam(required = false, name = "封面") String face,
            @ApiParam(required = true, name = "文章类型", example = "1系统文章 2广告文章") Integer articleType,
            @ApiParam(required = true, name = "文章二级分类 仅系统文章有效") Integer articleType2,
            @ApiParam(required = false, name = "文章内关联实体", example = "{\"articleattrId\":1,\"articleId\":1,\"itemType\":1,\"itemId\":1,\"location\":\"loc1\",\"orderIndex\":0}") String attr,
            @ApiParam(required = true, name = "文章内容") String content,
            @ApiIgnore User user
    ) {

        return null;
    }

}
