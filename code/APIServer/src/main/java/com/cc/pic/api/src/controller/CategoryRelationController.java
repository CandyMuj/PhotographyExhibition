package com.cc.pic.api.src.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName MyTest
 * @FileName CategoryRelationController
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/categoryRelation")
@Api(tags = "分类标签与实体的关联表，想了许久是直接在业务bean中加一个标签字段，还是使用专门的关系表来进行管理，最后决定使用关系表，就是分表吧")
public class CategoryRelationController {

}
