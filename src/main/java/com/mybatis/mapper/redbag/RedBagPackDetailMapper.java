package com.mybatis.mapper.redbag;

import com.mybatis.domain.redbag.RedBagPackDetailModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created by yunkai on 2017/11/30.
 */
@Mapper
@Component
public interface RedBagPackDetailMapper {

    @Insert("INSERT INTO `redbag_t_pack_detail`( `pack_id`, `user_id`, `money` )"
            + " VALUES(@{packId}, @{userId}, @{money})")
    int create(RedBagPackDetailModel packDetail);

    @Update("UPDATE `redbag_t_pack_detail` SET `user_id` = @{param2}"
            + " WHERE `id` = @{param1}")
    int updateUserId(Integer id, Integer userId);

    @Select("SELECT * FROM `redbag_t_pack_detail` WHERE 1=1 "
            + " AND `money` = @{param1} "
            + " AND `pack_id` = @{param2} "
            + " AND `user_id` is null "
            + " limit 1")
    RedBagPackDetailModel queryByMoney(Double money, Integer packId);

}
