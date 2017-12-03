package com.mybatis.service.redbag;

import com.mybatis.domain.redbag.RedBagPackModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yunkai on 2017/11/30.
 */
public interface RedBagPackServiceI {

    /**
     * 生成发红包记录
     *
     * @param pack
     */
    public void create(RedBagPackModel pack);
}
