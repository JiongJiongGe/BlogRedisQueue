package com.mybatis.domain.redbag;

import java.io.Serializable;

/**
 * 红包model (总金额、总数量)
 *
 * Created by yunkai on 2017/11/30.
 */
public class RedBagPackModel implements Serializable{

    private Integer id;

    private Double money;   //红包总金额

    private Integer num;   //红包数量

    private Integer userId;  //发红包用户Id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
