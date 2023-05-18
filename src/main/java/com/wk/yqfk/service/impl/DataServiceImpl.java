package com.wk.yqfk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wk.yqfk.bean.*;
import com.wk.yqfk.mapper.AnnounceMapper;
import com.wk.yqfk.mapper.DataMapper;
import com.wk.yqfk.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataMapper dataMapper;


    @Override
    public void addData(User user) {
        dataMapper.addData(user);
    }

    @Override
    public User checkTianbao(String number, String today) {
        User user = dataMapper.checktianbao(number,today);
        return user;
    }

    @Override
    public PageVo<User> queryDateList(int pageNum, int pageSize, UserVo userVo) {
        List<String> numberList = dataMapper.queryNumberByDep(userVo.getClasses());
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = dataMapper.queryDateList(userVo,numberList);
        PageInfo<User> pageInfo =new PageInfo<>(userList);
        PageVo<User> userPageVo = new PageVo<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), userList);

        return userPageVo;
    }

    @Override
    public void update(User user) {
        dataMapper.update(user);
    }

    @Override
    public void del(String id) {
        dataMapper.del(id);
    }

    @Override
    public List<User> queryDateList2(UserVo userVo) {
        List<User> userList = dataMapper.queryDateList(userVo,null);
        return userList;
    }

    @Override
    public List<String> queryClasses() {
        List<String> classesList = dataMapper.queryClasses();
        return classesList;
    }

    @Override
    public PageVo<Student> queryStuData(int pageNum, int pageSize, StuVO stuVO) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> studentList = dataMapper.queryStuData(stuVO);
        PageInfo<Student> pageInfo =new PageInfo<>(studentList);
        PageVo<Student> studentPageVo = new PageVo<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), studentList);

        return studentPageVo;
    }

    @Override
    public void updateStu(Student stu) {
        dataMapper.updateStu(stu);
    }

    @Override
    public void delStu(String number) {
        dataMapper.delStu(number);
    }

    @Override
    public List<KSH> queryDateListKSH(int pageNum, int i, UserVo userVo) {
        List<String> numberList = dataMapper.queryNumberByDep(userVo.getClasses());
        PageHelper.startPage(pageNum,i);
        List<KSH> kshList = dataMapper.queryDateListKSH(userVo.getConditionsVo(),numberList);
        return kshList;
    }

    @Autowired
    AnnounceMapper announceMapper;

    @Override
    public List<KSH> queryDateListKSH2(int pageNum, int i, UserVo userVo) {
        //根据学院查询班级
        List<String> classList = announceMapper.queryClassByDep(userVo.getClasses());
        //根据班级查询学号列表
        List<String> numberList =  dataMapper.queryStuNumberByClassList(classList);

        PageHelper.startPage(pageNum,i);
        List<KSH> kshList = dataMapper.queryDateListKSH(userVo.getConditionsVo(),numberList);
        return kshList;
    }
}
