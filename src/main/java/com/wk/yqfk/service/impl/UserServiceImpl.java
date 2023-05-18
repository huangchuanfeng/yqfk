package com.wk.yqfk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wk.yqfk.bean.*;
import com.wk.yqfk.mapper.UserMapper;
import com.wk.yqfk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    UserMapper userMapper;

    @Override
    public void addStu(Student stu) {
        userMapper.addStu(stu);
    }

    @Override
    public Student getStu(String number, String password) {
        return userMapper.getStu(number,password);
    }

    @Override
    public Student checkNumber(String number) {
        return userMapper.checkNumber(number);
    }

    @Override
    public void updateStu(Student stu) {
        userMapper.updateStu(stu);
    }

    @Override
    public Student queryStu(String number) {
        return userMapper.checkNumber(number);
    }

    @Override
    public void addAdmin2(Teacher tea) {
        if (!"3".equals(tea.getLevel())){
            tea.setLevel("2");
        }
        tea.setDel(0);
        tea.setPassword("123456");
        userMapper.addAdmin2(tea);
    }

    @Override
    public Teacher queryTea(String teaid) {
        return userMapper.queryTea(teaid);
    }

    @Override
    public Teacher getTea(String teaid, String password) {
        return userMapper.getTea(teaid,password);
    }

    @Override
    public List<Dep> queryAllDep() {
        return userMapper.queryAllDep();
    }

    @Override
    public List<Classes> queryClassesByDep(String dep) {
        return userMapper.queryClassesByDep(dep);
    }

    @Override
    public void updateTea(Teacher t) {
        userMapper.updateTea(t);
    }


    @Override
    public PageVo<Teacher> queryAllAdmin(int pageNum, int pageSize, String level) {
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> TeaList = userMapper.queryAllAdmin(level);
        PageInfo<Teacher> pageInfo =new PageInfo<>(TeaList);
        PageVo<Teacher> userPageVo = new PageVo<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), TeaList);
        return userPageVo;
    }

}
