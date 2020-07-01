package com.cc.pic.api.src.enumc;

import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName api
 * @FileName UserTypeEnum
 * @Description 用户类型及客户端类型
 * @Author CandyMuj
 * @Date 2020/6/11 14:50
 * @Version 1.0
 */
public enum UserTypeEnum {
    ADMIN(1, "管理员"),
    ;

    // 用作接口描述接口所在端
    public static final String ADMIN_CLIENT_STR = "管理端-";
    public static final String PC_CLIENT_STR = "前端-";


    private Integer type;

    UserTypeEnum(Integer type, String msg) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static UserTypeEnum val(Integer type) {
        if (type != null) {
            for (UserTypeEnum typeEnum : UserTypeEnum.values()) {
                if (typeEnum.type.equals(type)) {
                    return typeEnum;
                }
            }
        }

        return null;
    }


    // 平台管理端所包含的用户类型
    private static final Set<UserTypeEnum> adminClientSet = new HashSet<>();
    // 前端用户端所包含的用户类型
    private static final Set<UserTypeEnum> userClientSet = new HashSet<>();

    static {
        adminClientSet.add(ADMIN);


    }


    /**
     * 后台管理端所包含的用户类型
     */
    public boolean isAdminClient() {
        return adminClientSet.contains(this);
    }

    /**
     * 前端所包含的用户类型
     */
    public boolean isUserClient() {
        return userClientSet.contains(this);
    }

}
