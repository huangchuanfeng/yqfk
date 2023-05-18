package com.wk.yqfk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wk.yqfk.bean.*;
import com.wk.yqfk.mapper.AnnounceMapper;
import com.wk.yqfk.mapper.UserMapper;
import com.wk.yqfk.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class AnnounceServiceImpl implements AnnounceService {
    @Autowired
    AnnounceMapper announceMapper;
    @Override
    public List<Announce> queryAllAnn() {
        List<Announce> list1 = announceMapper.queryAllAnn();
        return list1;
    }

    @Override
    public List<Trip> queryAllTrip() {
        List<Trip> list1 = announceMapper.queryAllTrip();
        return list1;
    }

    @Override
    public Announce queryAnnounceByClasses(String classes) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Announce> list = announceMapper.queryAnnounceByClasses(classes,formatter.format(date));
        System.out.println(list);
        if (list.size()>0){
            return list.get(0);
        }else return null;
    }

    @Override
    public void addAnn(Announce ann) {
        ann.setDel(0);
        ann.setRes("待批准");
        String dep = announceMapper.queryDepByClass(ann.getClasses());
        String checkId = announceMapper.queryTeaByDep(dep);
        ann.setCheckid(checkId);
        announceMapper.addAnn(ann);

    }

    @Override
    public List<Announce> queryAnnByCheckid(String id) {
        List<Announce> list = announceMapper.queryAnnByCheckid(id);
        return list;
    }

    @Override
    public void updateAnn(String id, String res) {
        announceMapper.updateAnn(id,res);
    }

    @Override
    public void delAnn(String id) {
        announceMapper.delAnn(id);
    }

    @Autowired
    UserMapper userMapper;
    @Override
    public void addTrip(Trip t) {
        Teacher tea= userMapper.queryTeaByClass(t.getClasses());
        t.setTeaid(tea.getTeaid());
        t.setTeaname(tea.getTeaname());
        t.setStatus("待审批");
        announceMapper.addTrip(t);
    }

    @Override
    public PageVo<Trip> queryAllTripByTJ(int pageNum, int pageSize, Trip t) {
        PageHelper.startPage(pageNum,pageSize);
        List<Trip> tripList = announceMapper.queryAllTripByTJ(t);
        PageInfo<Trip> pageInfo =new PageInfo<>(tripList);
        PageVo<Trip> res = new PageVo<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), tripList);
        return res;
    }

    @Override
    public void delTrip(Trip t) {
        announceMapper.delTrip(t);
    }

    @Override
    public void updateTrip(Trip t) {
        announceMapper.updateTrip(t);
    }

    @Override
    public PageVo<Trip> queryAllTripByTJ2(int pageNum, int pageSize, Trip t, String dep) {

        List<String> classList = announceMapper.queryClassByDep(dep);
        List<String> teaidList = announceMapper.queryTeaByClassList(classList);
        PageHelper.startPage(pageNum,pageSize);
        List<Trip> tripList = announceMapper.queryAllTripByTJ2(teaidList);
        PageInfo<Trip> pageInfo =new PageInfo<>(tripList);
        PageVo<Trip> res = new PageVo<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(),
                pageInfo.getPages(), tripList);
        return res;
    }
}
