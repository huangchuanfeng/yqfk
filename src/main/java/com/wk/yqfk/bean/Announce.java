package com.wk.yqfk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announce {
    private Integer id;
    private String teaid;
    private String checkid;
    private String createtime;
    private String endtime;
    private String res;
    private String content;
    private Integer del ;
    private String title;
    private String classes;
    private String teaname;
}
