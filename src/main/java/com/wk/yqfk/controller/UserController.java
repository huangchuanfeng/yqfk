package com.wk.yqfk.controller;

import com.wk.yqfk.bean.*;
import com.wk.yqfk.exception.EtLoginException;
import com.wk.yqfk.exception.NumberException;
import com.wk.yqfk.service.AreaService;
import com.wk.yqfk.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/toLogin")
    public String toLoginPage(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        @RequestParam String number,
                        @RequestParam String password,
                        @RequestParam String isadmin,
                        @RequestParam String code) throws EtLoginException {
        System.out.println(number +"+++"+ password+"==="+isadmin);
        //比较验证码
        HttpSession session = request.getSession();
        String sessionCode =(String) session.getAttribute("code");
        if(!StringUtils.equals(sessionCode,code)){
            System.out.println(code);
            throw new EtLoginException("验证码错误");
        }
        session.invalidate();
        session = request.getSession();

        Teacher tea;
        Student stu;
        if ("1".equals(isadmin)){
            tea = userService.getTea(number,password);
            if (tea ==  null){
                throw new EtLoginException("用户名或者密码错误");
            }
            session.setAttribute("user",tea);
            if("1".equals(tea.getLevel())){
                return "redirect:/user/toAdminIndex";
            }else if ("2".equals(tea.getLevel())){
                //回到主页
                return "redirect:/jump/toAdminIndex2";
            }else {
                //回到主页
                return "redirect:/jump/toAdminIndex3";
            }
        }else{
            //查询数据库
            stu = userService.getStu(number, password);
            if (stu ==  null){
                throw new EtLoginException("用户名或者密码错误");
            }
            session.setAttribute("user",stu);
            return "redirect:/";
        }

    }

    @RequestMapping("toAdminIndex")
    public String toAdmin(){
        return "adminIndex";
    }


    @RequestMapping("toReg")
    public String toReg(){
        return "reg";
    }

    @RequestMapping("reg")
    public String regStu(Student stu){
        userService.addStu(stu);
        return "/login";
    }
    @RequestMapping("checkNumber")
    @ResponseBody
    public String checkNumber(@RequestParam String number) {
        Student stu = userService.checkNumber(number);
        if(stu != null){
            return "true";

        }else {
            return "false";
        }
    }

    @Autowired
    AreaService areaService;

    @ResponseBody
    @RequestMapping("/sheng")
    public List<Area> querySheng(){
        return areaService.querySheng();
    }

    @ResponseBody
    @RequestMapping("/shi")
    public List<Area> querySheng(String proname){
        return areaService.queryShi(proname);
    }

    @ResponseBody
    @RequestMapping("/qu")
    public List<Area> queryQu(String cityname){
        return areaService.queryQu(cityname);
    }

    @ResponseBody
    @RequestMapping("/queryAllDep")
    public List<Dep> queryAllDep(){
        return userService.queryAllDep();
    }

    @ResponseBody
    @RequestMapping("/queryClassesByDep")
    public List<Classes> queryClassesByDep(String dep){
        return userService.queryClassesByDep(dep);
    }

    @RequestMapping("updateStuData")
    public String updateStuData(Student stu){
        System.out.println("确认修改的stu信息"+stu);
        userService.updateStu(stu);
        return "updateSuccess";
    }

    @RequestMapping("checkUser")
    @ResponseBody
    public Student queryUserData(HttpServletRequest request){
        HttpSession session = request.getSession();
        Student stu =(Student) session.getAttribute("user");
        String number = stu.getNumber();
        Student stu1 = userService.queryStu(number);
        return stu1;
    }
    @RequestMapping("addAdmin2")
    @ResponseBody
    public String addAdmin2(Teacher tea){
        //先查询该教师号有无占用
        Teacher teacher = userService.queryTea(tea.getTeaid());
        System.out.println(teacher);
        if(teacher!=null){
            return "fail";
        }
        //没有存在则添加
        userService.addAdmin2(tea);
        return "success";
    }
    @RequestMapping("updateAdmin")
    public String updateAdmin(Teacher t){
        System.out.println("确认修改的信息"+t);
        userService.updateTea(t);
        return "updateSuccess";
    }

    @RequestMapping("queryAllAdmin")
    @ResponseBody
    public PageVo<Teacher> queryAllAdmin(@RequestParam(required = false,defaultValue = "1") int pageNumber,
                                @RequestParam(required = false,defaultValue = "3") int pageSize,
                                String level){
        int pageNum = pageNumber;
        PageVo<Teacher> pageVo= userService.queryAllAdmin(pageNum,pageSize,level);
        return pageVo;
    }
}
