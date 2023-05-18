package com.wk.yqfk.service;

import com.wk.yqfk.bean.Announce;
import com.wk.yqfk.bean.PageVo;
import com.wk.yqfk.bean.Trip;

import java.util.List;

public interface AnnounceService {
    List<Announce> queryAllAnn();
    List<Trip> queryAllTrip();

    Announce queryAnnounceByClasses(String classes);

    void addAnn(Announce ann);

    List<Announce> queryAnnByCheckid(String id);

    void updateAnn(String id, String res);

    void delAnn(String id);

    void addTrip(Trip t);

    PageVo<Trip> queryAllTripByTJ(int pageNum, int pageSize, Trip t);

    void delTrip(Trip t);

    void updateTrip(Trip t);

    PageVo<Trip> queryAllTripByTJ2(int pageNum, int pageSize, Trip t, String dep);
}
