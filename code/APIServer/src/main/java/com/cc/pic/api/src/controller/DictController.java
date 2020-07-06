package com.cc.pic.api.src.controller;

import com.cc.pic.api.annotations.Ann;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.enumc.DictLimitEnum;
import com.cc.pic.api.src.pojo.Dict;
import com.cc.pic.api.src.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<?> addOrUpd(
            @ApiParam(required = false, value = "字典id 如果有值则表示修改") @RequestParam(required = false) Long dictId,
            @ApiParam(required = true, value = "字典父级Id 无父级则为0") @RequestParam Long dictPid,
            @ApiParam(required = true, value = "字典名称") @RequestParam String dictName,
            @ApiParam(required = true, value = "字典编码") @RequestParam String dictCode,
            @ApiParam(required = true, value = "排序 越大越靠前 默认0") @RequestParam(defaultValue = "0") Long orderIndex,
            @ApiParam(required = false, value = "备注") @RequestParam(required = false) String remark,
            @ApiParam(required = false, value = "扩展数据") @RequestParam(required = false) String extData,
            @ApiParam(required = true, value = "自身限制 枚举值") @RequestParam Integer selfLimit,
            @ApiParam(required = true, value = "子级限制 枚举值") @RequestParam Integer childrenLimit
    ) {
        if (DictLimitEnum.val(selfLimit) == null || DictLimitEnum.val(childrenLimit) == null) {
            return Result.Error("错误的限制级别");
        }


        Dict dict = new Dict();
        dict.setDictId(dictId);
        dict.setDictPid((dictPid == null || dictPid < 0) ? 0 : dictPid);
        dict.setDictName(dictName);
        dict.setDictCode(dictCode);
        dict.setOrderIndex(orderIndex);
        dict.setRemark(remark);
        dict.setExtData(extData);
        dict.setSelfLimit(selfLimit);
        dict.setChildrenLimit(childrenLimit);

        return dictService.addOrUpd(dict);
    }

    @ApiOperation("获取字典列表-获取所有,前端根据缓存再做其他的获取")
    @GetMapping("/all")
    public Result<List<Dict>> all() {
        return dictService.all();
    }

    @Ann
    @ApiOperation("删除字典-若删除的是父节点，那么子节点将同步删除")
    @PostMapping("/del")
    public Result<?> del(
            @ApiParam(required = true, value = "字典id") @RequestParam Long dictId
    ) {
        return dictService.del(dictId);
    }

}
