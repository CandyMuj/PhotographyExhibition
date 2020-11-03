package com.cc.pic.api.src.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.config.CacheKey;
import com.cc.pic.api.src.enumc.DictLimitEnum;
import com.cc.pic.api.src.mapper.DictMapper;
import com.cc.pic.api.src.pojo.Dict;
import com.cc.pic.api.src.service.IDictService;
import com.cc.pic.api.utils.sys.bean.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private RedisUtil redisUtil;


    /**
     * 新增或修改dict，如果dictid大于0则表示修改
     *
     * @param dict
     * @return
     */
    @Override
    public Result<?> addOrUpd(Dict dict) {
        Dict pdict = null;
        if (dict.getDictPid() > 0) {
            pdict = getParent(dict.getDictPid());
            dict.setDictType(pdict.getDictCode());

            pdict = super.selectById(dict.getDictPid());
            if (pdict == null) {
                return Result.Error("错误的pid");
            }
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

            // 清缓存
            this.delCache();
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

            // 清缓存
            this.delCache();
            return dict.insert() ? Result.OK() : Result.Error("新增失败");
        }
    }

    /**
     * 删除字典
     * 若删除的是父节点，那么子节点将同步删除
     *
     * @param dictId
     * @return
     */
    @Override
    @Transactional
    public Result<?> del(Long dictId) {
        Dict dict = super.selectById(dictId);
        if (dict == null) {
            return Result.Error("不存在的字典");
        }
        Dict pdict = null;
        if (dict.getDictPid() > 0) {
            pdict = super.selectById(dict.getDictPid());
            if (pdict == null) {
                return Result.Error("错误的pid");
            }
        }


        // 验证此节点是否可被删除
        boolean limit = false;
        if (pdict == null) {
            limit = true;
        } else if (pdict.getChildrenLimit().equals(DictLimitEnum.ADW.getType()) || pdict.getChildrenLimit().equals(DictLimitEnum.DW.getType())) {
            limit = true;
        } else if (dict.getSelfLimit().equals(DictLimitEnum.ADW.getType()) || dict.getSelfLimit().equals(DictLimitEnum.DW.getType())) {
            limit = true;
        }
        if (!limit) {
            return Result.Error("节点限制，无权删除");
        }

        if (dict.deleteById() && super.delete(new EntityWrapper<Dict>().eq("dict_pid", dict.getDictId()))) {
            // 清缓存
            this.delCache();
            return Result.OK();
        }

        return Result.Error("删除失败");
    }

    /**
     * 清除缓存
     */
    private void delCache() {
        redisUtil.del(CacheKey.DICT_CACHE + "*");
    }

    /**
     * 保存缓存
     */
    private void saveCache() {
        // 先清空数据，因为根据id，code获取的时候可能确实没有那个键对应的值
        // 那么这时候来设置数据，但是之前的老数据在没有修改的情况下是没有清除的，覆盖时可能会有问题
        delCache();

        List<Dict> dictList = super.selectList(new EntityWrapper<Dict>().orderBy("order_index", false));
        if (dictList != null && dictList.size() > 0) {
            // 所有的list
            redisUtil.set(CacheKey.DICT_CACHE_ALL, dictList);

            Map<String, List<Dict>> typeMap = new HashMap<>();
            Map<Long, List<Dict>> pidMap = new HashMap<>();
            for (Dict dict : dictList) {
                // Map<String, Dict>
                redisUtil.set(CacheKey.DICT_CACHE_CODE + dict.getDictCode(), dict);
                // Map<Integer, Dict>
                redisUtil.set(CacheKey.DICT_CACHE_ID + dict.getDictId(), dict);

                List<Dict> typeList = typeMap.computeIfAbsent(dict.getDictType(), k -> new ArrayList<>());
                typeList.add(dict);

                List<Dict> pidList = pidMap.computeIfAbsent(dict.getDictPid(), k -> new ArrayList<>());
                pidList.add(dict);
            }


            for (Map.Entry<String, List<Dict>> e : typeMap.entrySet()) {
                redisUtil.set(CacheKey.DICT_CACHE_TYPE + e.getKey(), e.getValue());
            }

            for (Map.Entry<Long, List<Dict>> e : pidMap.entrySet()) {
                redisUtil.set(CacheKey.DICT_CACHE_PID + e.getKey(), e.getValue());
            }
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
        Dict dict = byId(dictId);
        if (dict == null) {
            return null;
        }
        if (dict.getDictPid() == 0) {
            return dict;
        }

        return getParent(dict.getDictPid());
    }

    /**
     * 获取所有的字典
     *
     * @return
     */
    @Override
    public List<Dict> all() {
        List<Dict> list = (List<Dict>) redisUtil.get(CacheKey.DICT_CACHE_ALL);

        if (list == null) {
            saveCache();
            list = (List<Dict>) redisUtil.get(CacheKey.DICT_CACHE_ALL);
        }

        return list;
    }

    @Override
    public List<Dict> byPid(Long pid) {
        if (pid == null || pid < 0) {
            return new ArrayList<>();
        }

        List<Dict> list = (List<Dict>) redisUtil.get(CacheKey.DICT_CACHE_PID + pid);
        if (list == null) {
            saveCache();
            list = (List<Dict>) redisUtil.get(CacheKey.DICT_CACHE_PID + pid);
        }

        return list;
    }

    @Override
    public List<Dict> byType(String type) {
        if (StrUtil.isBlank(type)) {
            return new ArrayList<>();
        }

        List<Dict> list = (List<Dict>) redisUtil.get(CacheKey.DICT_CACHE_TYPE + type);
        if (list == null) {
            saveCache();
            list = (List<Dict>) redisUtil.get(CacheKey.DICT_CACHE_TYPE + type);
        }

        return list;
    }

    @Override
    public Dict byId(Long id) {
        if (id == null || id < 0) {
            return null;
        }

        Dict dict = (Dict) redisUtil.get(CacheKey.DICT_CACHE_ID + id);
        if (dict == null) {
            saveCache();
            dict = (Dict) redisUtil.get(CacheKey.DICT_CACHE_ID + id);
        }

        return dict;
    }

    @Override
    public Dict byCode(String code) {
        if (StrUtil.isBlank(code)) {
            return null;
        }

        Dict dict = (Dict) redisUtil.get(CacheKey.DICT_CACHE_CODE + code);
        if (dict == null) {
            saveCache();
            dict = (Dict) redisUtil.get(CacheKey.DICT_CACHE_CODE + code);
        }

        return dict;
    }

}
