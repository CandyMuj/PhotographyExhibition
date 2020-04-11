package com.cc.pic.api.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.cc.pic.api.pojo.sys.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @ProJectName APIServer
 * @FileName DB
 * @Description 关于数据库操作相关的工具
 * @Author CandyMuj
 * @Date 2020/4/11 12:43
 * @Version 1.0
 */
@Slf4j
public class DB {

    /**
     * 手动回滚事务
     */
    public static void setRollbackOnly() {
        try {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (NoTransactionException e) {
            log.warn("当前没有事务存在");
        }
    }

    /**
     * mybatis-plus 传入page解析成result对象返回
     *
     * @param page
     * @return
     */
    public static Result getPageRes(Page page) {
        Result result = new Result<>(page.getRecords());
        result.curPage = page.getCurrent();
        result.pageSize = page.getSize();
        result.totalCount = (long) page.getTotal();
        return result;
    }

    /**
     * pagehelper 传入page解析成result对象返回
     *
     * @param page
     * @return
     */
    public static Result getPageRes(com.github.pagehelper.Page page) {
        Result result = new Result<>(page.getResult());
        result.curPage = page.getPageNum();
        result.pageSize = page.getPageSize();
        result.totalCount = page.getTotal();
        return result;
    }

}
