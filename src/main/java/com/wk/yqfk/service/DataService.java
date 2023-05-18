package com.wk.yqfk.service;

import com.wk.yqfk.bean.*;

import java.util.Date;
import java.util.List;

public interface DataService {
    void addData(User user);

    User checkTianbao(String number, String today);

    PageVo<User> queryDateList(int pageNum, int pageSize, UserVo userVo);

    void update(User user);

    void del(String id);

    List<User> queryDateList2(UserVo userVo);

    List<String> queryClasses();

    PageVo<Student> queryStuData(int pageNum, int pageSize, StuVO stuVO);

    void updateStu(Student stu);

    void delStu(String number);

    List<KSH> queryDateListKSH(int pageNum, int i, UserVo userVo);

    List<KSH> queryDateListKSH2(int pageNum, int i, UserVo userVo);
}
