package com.cc.pic.api.enumc.sys;

/**
 * @ProJectName APIServer
 * @FileName TokenGenerateEnum
 * @Description token 生成策略;一个用户可能有多个有效的token，但是一个token只对应一个用户
 * @Author CandyMuj
 * @Date 2019/12/30 17:22
 * @Version 1.0
 */
public enum TokenGenerateEnum {
    // 每次都生成唯一的token；每次获取一个新的token，且之前的token不失效(意味着一个用户只要token未过期 那么同时存在多个有效的token)
    ONLY_ALIVE,
    // 每次都生成唯一的token；每次获取一个新的token，且之前的token失效(意味着在未过期的情况下，同时只有一个token)
    ONLY_DEATH,
    // 不生成新的token；如果之前的token已失效才获取新的token
    OLD

}
