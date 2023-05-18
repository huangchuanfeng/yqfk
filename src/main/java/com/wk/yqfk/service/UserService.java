package com.wk.yqfk.service;

import com.wk.yqfk.bean.*;

import java.util.List;

public interface UserService {
    void addStu(Student stu);

    Student getStu(String number, String password);

    Student checkNumber(String number);

    void updateStu(Student stu);

    Student queryStu(String number);

    void addAdmin2 (Teacher tea);

    Teacher queryTea(String teaid);

    Teacher getTea(String teaid, String password);

    List<Dep> queryAllDep();

    List<Classes> queryClassesByDep(String dep);

    void updateTea(Teacher t);

    PageVo<Teacher> queryAllAdmin(int pageNum, int pageSize, String level);
}
