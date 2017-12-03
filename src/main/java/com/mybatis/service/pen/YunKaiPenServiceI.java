package com.mybatis.service.pen;

import com.mybatis.domain.pen.YunKaiPenModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yunkai on 2017/11/16.
 */
public interface YunKaiPenServiceI {

    /**
     * 获取所有的笔列表信息
     *
     * @return
     */
    public List<YunKaiPenModel> findPenList();

    /**
     * 更新笔库存
     *
     * @param id  笔Id
     * @return
     */
    @Transactional
    public int updatePenNum(String id);

    /**
     * 获取笔库存
     *
     * @param id  笔Id
     * @return
     */
    public int getNumById(String id);
}
