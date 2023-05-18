package com.wk.yqfk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String teaid;
    private String teaname;
    private String phone;
    private String dep;
    private String bossid;
    private String level;
    private String password;
    private int del;
}
