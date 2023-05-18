package com.wk.yqfk.mapper;

import com.wk.yqfk.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    Student getStu(@Param("number")String number, @Param("password")String password);

    void addStu(Student stu);

    Student checkNumber(String number);

    void updateStu(Student stu);

    void addAdmin2(Teacher tea);

    Teacher queryTea(String teaid);

    Teacher getTea(@Param("teaid")String teaid, @Param("password")String password);

    List<Dep> queryAllDep();

    List<Classes> queryClassesByDep(@Param("dep") String dep);

    void updateTea(Teacher t);

    List<Teacher> queryAllAdmin(@Param("level") String level);

    Teacher queryTeaByClass(String classes);
}
