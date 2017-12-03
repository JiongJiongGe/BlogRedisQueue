package com.mybatis.mapper.redbag;

import com.mybatis.domain.redbag.RedBagPackModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by yunkai on 2017/11/30.
 */
@Mapper
@Component
public interface RedBagPackMapper {

    @Insert("INSERT INTO `redbag_t_pack`( `money`, `num`, `user_id` )"
            + " VALUES (@{money}, @{num}, @{userId})")
    int create(RedBagPackModel pack);

    @Select("SELECT LAST_INSERT_ID() AS ID")
    int last_insert_id();
}
