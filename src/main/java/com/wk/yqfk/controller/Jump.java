package com.wk.yqfk.controller;

import com.wk.yqfk.bean.Student;
import com.wk.yqfk.bean.Teacher;
import com.wk.yqfk.bean.User;
import com.wk.yqfk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/jump")
public class Jump {

    @Autowired
    UserService userService;

    @RequestMapping("/welcome")
    public String toWelcome(){
        return "welcome";
    }
    @RequestMapping("updateimg")
    public String toUpdateImg(){
        return "updateimg";
    }
    @RequestMapping("updateData")
    public String toUpdateData(){
        return "updateData";
    }
    @RequestMapping("updateData1")
    public String toUpdateData1(){
        return "updateData1";
    }
    @RequestMapping("checkUser")
    @ResponseBody
    public Object queryUserData(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") instanceof com.wk.yqfk.bean.Teacher){
            Teacher tea =(Teacher) session.getAttribute("user");
            String teaid = tea.getTeaid();
            Teacher teaRes = userService.queryTea(teaid);
            return teaRes;
        }
        Student stu =(Student) session.getAttribute("user");
        String number = stu.getNumber();
        Student stu1 = userService.queryStu(number);
        return stu1;
    }

    @RequestMapping("contacts")
    public String jumpContacts(){
        return "contacts";
    }

    @RequestMapping("index")
    public String jumpIndex(){
        return "index";
    }

    @RequestMapping("toForm")
    public String jumpForm(){
        return "form";
    }
    @RequestMapping("toGLStu")
    public String jumpGLStu(){
        return "adminGLStu";
    }
    @RequestMapping("toStuData")
    public String jumpStuData(){
        return "checkStu";
    }
    @RequestMapping("toEcharsData")
    public String jumpEchars(){
        return "EcharsData";
    }
    @RequestMapping("toShowData")
    public String toShowData(){
        return "showDataKSH";
    }
    @RequestMapping("toAnnounceList")
    public String toAnnounceList(){
        return "announceList";
    }
    @RequestMapping("toAnnounce")
    public String toAnnounce(){
        return "announce";
    }
    @RequestMapping("toTrip")
    public String toTrip(){
        return "trip";
    }
    @RequestMapping("tocheckstu1C")
    public String tocheckstu1C(){
        return "checkStu1C";
    }
    @RequestMapping("tochecktrip3")
    public String tochecktrip3(){
        return "checkTrip3";
    }
    @RequestMapping("toManageAdmin")
    public String toManageAdmin(){
        return "manageAdmin";
    }
    @RequestMapping("toManageAdmin3")
    public String toManageAdmin3(){
        return "manageAdmin3";
    }
    @RequestMapping("toAdminIndex2")
    public String toAdminIndex2(){
        return "adminIndex2";
    }
    @RequestMapping("toAdminIndex3")
    public String toAdminIndex3(){
        return "adminIndex3";
    }
    @RequestMapping("toStuData3")
    public String toStuData3(){
        return "stuData3";
    }
    @RequestMapping("toGLStu3")
    public String toGLStu3(){
        return "GLStu3";
    }
    @RequestMapping("toStuGLTrip")
    public String toStuGLTrip(){
        return "stuGLTrip";
    }
    @RequestMapping("tochecktrip2")
    public String tochecktrip2(){
        return "checkTrip2";
    }
}
