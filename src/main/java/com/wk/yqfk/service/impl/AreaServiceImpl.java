package com.wk.yqfk.service.impl;

import com.wk.yqfk.bean.Area;
import com.wk.yqfk.mapper.AreaMapper;
import com.wk.yqfk.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaMapper areaMapper;

    @Override
    public List<Area> querySheng() {
        return areaMapper.querySheng();
    }

    @Override
    public List<Area> queryShi(String proname) {
        return areaMapper.queryShi(proname);
    }

    @Override
    public List<Area> queryQu(String cityname) {
        return areaMapper.queryQu(cityname);
    }
}
