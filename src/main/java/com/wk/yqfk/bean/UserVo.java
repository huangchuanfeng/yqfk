package com.wk.yqfk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String numberVo ;
    private String nameVo ;
    private String startDateVo ;
    private String endDateVo ;
    private String conditionsVo ;
    private Date startDate ;
    private Date endDate ;
    private List<String> numberList;
    private String classes;
}
