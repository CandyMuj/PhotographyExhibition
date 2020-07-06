package com.cc.pic.api.src.service;

import com.baomidou.mybatisplus.service.IService;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.pojo.Dict;

import java.util.List;

/**
 * @ProjectName PhotographyExhibition
 * @FileName IDictService
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
public interface IDictService extends IService<Dict> {

    Result<?> addOrUpd(Dict dict);

    Dict getParent(Long dictId);

    Result<List<Dict>> all();

    Result<?> del(Long dictId);
}
