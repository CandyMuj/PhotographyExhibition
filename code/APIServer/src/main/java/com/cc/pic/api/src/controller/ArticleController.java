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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @ProjectName PhotographyExhibition
 * @FileName ArticleController
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
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
            @ApiParam(required = false, name = "文章id 如果有值则表示更新") @RequestParam(required = false) Long articleId,
            @ApiParam(required = true, name = "文章标题") @RequestParam String title,
            @ApiParam(required = false, name = "封面") @RequestParam(required = false) String coverImg,
            @ApiParam(required = true, name = "文章类型", example = "1系统文章... 枚举值") @RequestParam Integer articleType,
            @ApiParam(required = true, name = "文章二级分类 仅系统文章有效必填 取字典表 其他传0") @RequestParam Integer articleType2,
            @ApiParam(required = true, name = "是否展示 默认不展示", example = "1展示 0不展示") @RequestParam(defaultValue = "0") Integer enabled,
            @ApiParam(required = false, name = "文章内关联实体", example = "{\"attrId\":1,\"itemType\":1,\"itemId\":1,\"location\":\"loc1\",\"orderIndex\":0}") @RequestParam(required = false) String attr,
            @ApiParam(required = true, name = "文章内容") @RequestParam String content,
            @ApiIgnore User user
    ) {

        return null;
    }

}
