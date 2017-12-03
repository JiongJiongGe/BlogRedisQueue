package com.mybatis.domain.pen;

import java.io.Serializable;

/**
 * Created by yunkai on 2017/11/16.
 */
public class YunKaiPenModel implements Serializable{

    private Short id;

    private String penName;  //笔名称

    private Integer penNum;  //笔数量

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public Integer getPenNum() {
        return penNum;
    }

    public void setPenNum(Integer penNum) {
        this.penNum = penNum;
    }
}
