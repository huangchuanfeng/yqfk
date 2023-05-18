package com.wk.yqfk.mapper;

import com.wk.yqfk.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DataMapper {

    void addData(User user);

    User checktianbao(@Param("number") String number,@Param("today") String  today);

    List<User> queryDateList(@Param("userVo") UserVo userVo,@Param("list") List<String> numberList);

    void update(User user);

    void del(String id);

    List<String> queryClasses();

    List<Student> queryStuData(StuVO stuVO);

    void updateStu(Student stu);

    void delStu(String number);

    List<String> queryNumberByDep(String classesVo);

    List<KSH> queryDateListKSH(@Param("conditionsVo")String conditionsVo, @Param("list") List<String> numberList);


    List<String> queryStuNumberByClassList(List<String> classList);
}
