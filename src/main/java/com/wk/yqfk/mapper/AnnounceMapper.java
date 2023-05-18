package com.wk.yqfk.mapper;

import com.wk.yqfk.bean.Announce;
import com.wk.yqfk.bean.Trip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnounceMapper {
    List<Announce> queryAllAnn();
    List<Trip> queryAllTrip();

    List<Announce> queryAnnounceByClasses(@Param("classes") String classes,@Param("date") String date);

    String queryDepByClass(String classes);

    String queryTeaByDep(String dep);

    void addAnn(Announce ann);

    List<Announce> queryAnnByCheckid(String id);

    void updateAnn(@Param("id") String id, @Param("res") String res);

    void delAnn(String id);

    void addTrip(Trip t);


    List<Trip> queryAllTripByTJ(Trip t);

    void delTrip(Trip t);

    void updateTrip(Trip t);

    List<String> queryClassByDep(String dep);

    List<String> queryTeaByClassList(@Param("list") List<String> classList);

    List<Trip> queryAllTripByTJ2(@Param("list") List<String> teaidList);
}
