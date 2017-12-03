package com.mybatis.mapper.pen;

import com.mybatis.domain.pen.YunKaiPenModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yunkai on 2017/11/16.
 */
@Mapper
@Component
public interface YunKaiPenMapper {

    @Select("SELECT * FROM `calmwang_t_pen`")
    List<YunKaiPenModel> queryPenList();

    @Update("UPDATE `calmwang_t_pen` SET pen_num = pen_num - 1 WHERE 1=1 "
            + " AND id = @{param1} AND `pen_num` > 0")
    int updatePenNum(String id);

    @Select("SELECT pen_num FROM `calmwang_t_pen` WHERE `id` = @{param1}")
    int queryNumById(String id);
}
