package com.wk.yqfk.bean;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private String id ;
    private String number ;
    private String name ;
    private String idcard ;
    private String phone ;
    private String conditions ;
    private Integer del ;
    private Double temperature;
    private String address;
    private String address_now;
    private String s_createtime ;
    private String s_intime ;
    private Date createtime ;
    private Date intime ;
    private String iswailai;
    private String zhuangtai;
    /*private String shengName;
    private String shiName;
    private String quName;*/
}
