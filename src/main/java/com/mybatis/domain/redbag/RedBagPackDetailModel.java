package com.mybatis.domain.redbag;

import java.io.Serializable;

/**
 * 红包明细
 *
 * Created by yunkai on 2017/11/30.
 */
public class RedBagPackDetailModel implements Serializable{

    private Integer id;

    private Integer packId;  //红包Id

    private Integer userId;  //领取用户Id

    private Double money;  //红包金额

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
