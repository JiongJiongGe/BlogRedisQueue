package com.mybatis.service.impl.user;

import com.mybatis.mapper.user.YunKaiUserMapper;
import com.mybatis.service.user.YunKaiUserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yunkai on 2017/11/30.
 */
@Service("yunKaiUserService")
public class YunKaiUserServiceImpl implements YunKaiUserServiceI{

    @Autowired
    private YunKaiUserMapper yunKaiUserMapper;
}
