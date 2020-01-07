package com.cc.pic.api.src.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.pojo.Poster;
import com.cc.pic.api.src.mapper.PosterMapper;
import com.cc.pic.api.src.service.IPosterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ProJectName APIServer
 * @FileName IPosterServiceImpl
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/7 15:48
 * @Version 1.0
 */
@Slf4j
@Service
public class IPosterServiceImpl extends ServiceImpl<PosterMapper, Poster> implements IPosterService {

}
