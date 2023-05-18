package com.wk.yqfk.service;

import com.wk.yqfk.bean.Area;
import com.wk.yqfk.bean.Classes;

import java.util.List;

public interface AreaService {
    List<Area> querySheng();

    List<Area> queryShi(String proname);

    List<Area> queryQu(String cityname);

}
