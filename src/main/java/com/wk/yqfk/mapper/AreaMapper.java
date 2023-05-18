package com.wk.yqfk.mapper;

import com.wk.yqfk.bean.Area;

import java.util.List;

public interface AreaMapper {
    List<Area> querySheng();

    List<Area> queryShi(String name);

    List<Area> queryQu(String cityname);
}
