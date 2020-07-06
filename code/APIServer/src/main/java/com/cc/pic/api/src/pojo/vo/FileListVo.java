package com.cc.pic.api.src.pojo.vo;

import com.cc.pic.api.src.pojo.FileList;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ProjectName MyTest
 * @FileName FileList
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@ApiModel("文件统一管理库，如果是为了速度，可使用redis，但是总感觉redis不太可靠")
public class FileListVo extends FileList {

}