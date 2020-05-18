package com.cc.pic.api.src.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.enumc.DictLimitEnum;
import com.cc.pic.api.src.mapper.DictMapper;
import com.cc.pic.api.src.pojo.Dict;
import com.cc.pic.api.src.service.IDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ProjectName PhotographyExhibition
 * @FileName DictServiceImpl
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    /**
     * 新增或修改dict，如果dictid大于0则表示修改
     *
     * @param dict
     * @return
     */
    @Override
    public Result addOrUpd(Dict dict) {
        Dict pdict = null;
        if (dict.getDictPid() > 0) {
            pdict = getParent(dict.getDictPid());
            dict.setDictType(pdict.getDictCode());

            pdict = super.selectById(dict.getDictPid());
        } else {
            dict.setDictType(dict.getDictCode());
        }


        // 更新
        boolean limit = false;
        if (dict.getDictId() != null && dict.getDictId() > 0) {
            // 验证是否有更新权限
            if (pdict == null) {
                limit = true;
            } else if (!pdict.getChildrenLimit().equals(DictLimitEnum.R.getType())) {
                limit = true;
            } else if (!dict.getSelfLimit().equals(DictLimitEnum.R.getType())) {
                limit = true;
            }
            if (!limit) {
                return Result.Error("节点限制，无权修改");
            }

            return dict.updateById() ? Result.OK() : Result.Error("修改失败");
        }
        // 新增
        else {
            // 验证此级是否有权限新增
            if (pdict == null) {
                limit = true;
            } else if (pdict.getChildrenLimit().equals(DictLimitEnum.ADW.getType())) {
                limit = true;
            } else if (dict.getSelfLimit().equals(DictLimitEnum.ADW.getType())) {
                limit = true;
            }
            if (!limit) {
                return Result.Error("节点限制，无权新增");
            }

            return dict.insert() ? Result.OK() : Result.Error("新增失败");
        }
    }

    /**
     * 获取最父级的那个dict，及dictpid=0的那个
     *
     * @param dictId
     * @return
     */
    @Override
    public Dict getParent(Long dictId) {
        Dict dict = super.selectById(dictId);
        if (dict == null) {
            return null;
        }
        if (dict.getDictPid() == 0) {
            return dict;
        }

        return getParent(dict.getDictPid());
    }

}
