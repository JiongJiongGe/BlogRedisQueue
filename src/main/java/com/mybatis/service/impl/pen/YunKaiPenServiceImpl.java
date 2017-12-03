package com.mybatis.service.impl.pen;

import com.mybatis.domain.pen.YunKaiPenModel;
import com.mybatis.mapper.pen.YunKaiPenMapper;
import com.mybatis.service.pen.YunKaiPenServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yunkai on 2017/11/16.
 */
@Service("yunKaiPenService")
public class YunKaiPenServiceImpl implements YunKaiPenServiceI{

    @Autowired
    private YunKaiPenMapper yunKaiPenMapper;

    @Override
    public List<YunKaiPenModel> findPenList(){
        return yunKaiPenMapper.queryPenList();
    }

    @Override
    public int updatePenNum(String id){
        return yunKaiPenMapper.updatePenNum(id);
    }

    @Override
    public int getNumById(String id){
        return yunKaiPenMapper.queryNumById(id);
    }

}
