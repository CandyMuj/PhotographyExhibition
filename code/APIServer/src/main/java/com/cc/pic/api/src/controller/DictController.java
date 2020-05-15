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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ProjectName PhotographyExhibition
 * @FileName DictController
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/dict")
@Api(tags = "字典管理")
public class DictController {
    @Resource
    private IDictService dictService;


    @Ann
    @ApiOperation("添加或修改字典")
    @PostMapping("/addOrUpd")
    public Result addOrUpd(
            @ApiParam(required = false, name = "字典id 如果有值则表示修改") @RequestParam(required = false) Long dictId,
            @ApiParam(required = true, name = "字典父级Id 无父级则为0") @RequestParam Long dictPid,
            @ApiParam(required = true, name = "字典名称") @RequestParam String dictName,
            @ApiParam(required = true, name = "字典编码") @RequestParam String dictCode,
            @ApiParam(required = true, name = "排序 越大越靠前 默认0") @RequestParam(defaultValue = "0") Long orderIndex,
            @ApiParam(required = false, name = "备注") @RequestParam(required = false) String remark,
            @ApiParam(required = false, name = "扩展数据") @RequestParam(required = false) String extData,
            @ApiParam(required = true, name = "自身限制 枚举值") @RequestParam Integer selfLimit,
            @ApiParam(required = true, name = "子级限制 枚举值") @RequestParam Integer childrenLimit
    ) {

        return null;
    }


}
