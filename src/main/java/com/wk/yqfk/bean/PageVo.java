package com.wk.yqfk.bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {
    private int pageNum;
    private int pageSize;
    private long total;
    private int pageCount;
    private List<T> rows;
}