package com.wk.yqfk.controller;


import com.wk.yqfk.bean.Announce;
import com.wk.yqfk.bean.PageVo;
import com.wk.yqfk.bean.Teacher;
import com.wk.yqfk.bean.Trip;
import com.wk.yqfk.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/announce")
public class AnnounceController {
    @Autowired
    AnnounceService announceService;

    @RequestMapping("/queryAllAnn")
    @ResponseBody
    public List<Announce> queryAllAnn(){
        return announceService.queryAllAnn();
    }
    @RequestMapping("/queryAllTrip")
    @ResponseBody
    public List<Trip> queryAllTrip(){
        return announceService.queryAllTrip();
    }
    @RequestMapping("/queryAnnounceByClasses")
    @ResponseBody
    public Announce queryAnnounceByClasses(String classes){
        return announceService.queryAnnounceByClasses(classes);
    }
    @RequestMapping("/addAnn")
    @ResponseBody
    public void addAnn(Announce ann){
        announceService.addAnn(ann);
        return;
    }
    @RequestMapping("/queryAnnByCheckid")
    @ResponseBody
    public List<Announce> queryAnnByCheckid(String id){
        List<Announce> list = announceService.queryAnnByCheckid(id);
        return list;
    }
    @RequestMapping("/updateAnn")
    @ResponseBody
    public void updateAnn(String id,String res){
        announceService.updateAnn(id,res);
    }
    @RequestMapping("/delAnn")
    @ResponseBody
    public void delAnn(String id){
        announceService.delAnn(id);
    }

    @RequestMapping("/addTrip")
    @ResponseBody
    public void addTrip(Trip t){
        announceService.addTrip(t);
    }
    @RequestMapping("queryTrip")//  请求
    @ResponseBody               //响应体
    //参数：当前页、每页记录数、管理员级别、管理员系部
    public PageVo<Trip> queryTrip(@RequestParam(required = false,defaultValue = "1") int pageNumber,
                                         @RequestParam(required = false,defaultValue = "5") int pageSize,
                                        @RequestParam(required = false,defaultValue = "") String level,
                                        @RequestParam(required = false,defaultValue = "") String dep,
                                         Trip t){
        int pageNum = pageNumber;
        if ("2".equals(level)){
            if (!"".equals(dep)){       // 匹配管理员的级别和部门
                // 查询数据库
                PageVo<Trip> pageVo= announceService.queryAllTripByTJ2(pageNum,pageSize,t,dep);
                return pageVo;
            }

        }
        //返回封装好的响应
        PageVo<Trip> pageVo= announceService.queryAllTripByTJ(pageNum,pageSize,t);
        return pageVo;
    }
    @RequestMapping("delTrip")
    @ResponseBody
    public void delTrip(Trip t){
        announceService.delTrip(t);
    }
    @RequestMapping("updateTrip")
    @ResponseBody
    public void updateTrip(Trip t){
        if("清空".equals(t.getRereason())){
            t.setRereason(null);
        }
        announceService.updateTrip(t);
    }
}
