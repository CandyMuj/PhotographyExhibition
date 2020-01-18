package com.cc.pic.api.src.controller;

import com.cc.pic.api.annotations.Ann;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProJectName APIServer
 * @FileName DictController
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/16 17:01
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/comm/dict")
@Api(tags = {"通用", "字典管理"})
public class DictController {
    @Resource
    private IDictService dictService;


    @Ann
    @ApiOperation("添加或修改字典")
    @PostMapping("/addOrUpd")
    public Result addOrUpd(
            @ApiParam(required = false, name = "字典id 如果有值则表示修改") Integer articleId
    ) {

        return null;
    }

}
