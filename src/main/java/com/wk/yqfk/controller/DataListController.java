package com.wk.yqfk.controller;

import com.wk.yqfk.bean.*;
import com.wk.yqfk.service.DataService;
import com.wk.yqfk.service.UserService;
import com.wk.yqfk.utils.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/list")
public class DataListController {
    @Autowired
    UserService userService;

    @Autowired
    DataService dataService;

    @RequestMapping("update")
    @ResponseBody
    public String update(User user){
        user.setZhuangtai(checkzhuangtai(user));

        dataService.update(user);
        return "success";
    }

    @RequestMapping("del")
    @ResponseBody
    public String del(String id){
        dataService.del(id);
        return "success";
    }


    @RequestMapping("export")       //  请求
    public void  export(HttpServletRequest request, HttpServletResponse response ,
                        @RequestParam(required = false,defaultValue = "1") int pageNumber,      //后端分页，页数，默认第一页
                        @RequestParam(required = false,defaultValue = "3") int pageSize,        //每页记录数，默认3条
                        UserVo userVo) throws IOException
    {
        //1 构造Workbook对象
        Workbook wb = new SXSSFWorkbook();
        //2 添加sheet
        Sheet sheet = wb.createSheet("填报数据");
        //3 添加表头Row
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("学号");
        row.createCell(2).setCellValue("姓名");
        row.createCell(3).setCellValue("身份证号码");
        row.createCell(4).setCellValue("手机号码");
        row.createCell(5).setCellValue("状况");
        row.createCell(6).setCellValue("温度");
        row.createCell(7).setCellValue("发热情况");
        row.createCell(8).setCellValue("户籍地");
        row.createCell(9).setCellValue("现居住地");
        row.createCell(10).setCellValue("是否外来");
        row.createCell(11).setCellValue("进区时间");
        row.createCell(12).setCellValue("创建时间");
        //4 获得数据
        if("".equals(userVo.getNumberVo())){
            userVo.setNumberVo("0000");
        }
        List<User> data = dataService.queryDateList2(userVo);   //  获得数据库中数据
        int k = 1;
        for (User d : data) {
            Row row_data = sheet.createRow(k);
            row_data.createCell(0).setCellValue(d.getId());
            row_data.createCell(1).setCellValue(d.getNumber());
            row_data.createCell(2).setCellValue(d.getName());
            row_data.createCell(3).setCellValue(d.getIdcard());
            row_data.createCell(4).setCellValue(d.getPhone());
            row_data.createCell(5).setCellValue(d.getConditions());
            row_data.createCell(6).setCellValue(d.getTemperature());
            row_data.createCell(7).setCellValue(d.getZhuangtai());
            row_data.createCell(8).setCellValue(d.getAddress());
            row_data.createCell(9).setCellValue(d.getAddress_now());
            row_data.createCell(10).setCellValue(d.getIswailai());
            row_data.createCell(11).setCellValue(DateUtil.DateToString(d.getIntime()));
            row_data.createCell(12).setCellValue(DateUtil.DateToString(d.getCreatetime()));

            k++;
        }
        //6修改响应方式response.setHeader("Content-Type","attachement;filename")
        Date date = new Date();
        response.setHeader("Content-Disposition", "attachment;filename="+ DateUtil.DateToString(date) +".xlsx");
        //7 写出wb.write(response.getOutputStream())
        wb.write(response.getOutputStream());

        wb.close();
        
    }



    @RequestMapping("queryData")    //请求
    @ResponseBody                   //响应为json
    public PageVo<User> queryData(
            HttpServletRequest request,
            @RequestParam(required = false,defaultValue = "1") int pageNumber,      //后端分页，要查询哪一页，默认第一页
            @RequestParam(required = false,defaultValue = "3") int pageSize,    //后端分页，每页记录数，默认每页3条信息
            UserVo userVo       //接收的模糊查询的条件，已封装
    ){
        System.out.println("接收的条件"+userVo+"PageNumber:"+pageNumber+"PageSize:"+pageSize);
        int pageNum = pageNumber;
        HttpSession session = request.getSession();     // 获取seession，先看身份信息
        if (session.getAttribute("user") instanceof Teacher){
            Teacher tea = (Teacher) session.getAttribute("user");       //拿出信息要先强制转换一下
        }else {
            Student stu = (Student) session.getAttribute("user");
            userVo.setNumberVo(stu.getNumber());            //如果查询条件指定了学号，则查询指定学号的健康信息
        }
        PageVo<User> userPageVo = dataService.queryDateList(pageNum,pageSize,userVo);       //利用dao层去数据库查询
        return userPageVo;
    }

    @RequestMapping("queryKSH") //请求
    @ResponseBody           //响应体
    public List<KSH> queryKSH(
            //参数为：当前页、每页记录数、查询条件
            HttpServletRequest request,
            @RequestParam(required = false,defaultValue = "1") int pageNumber,
            @RequestParam(required = false,defaultValue = "3000") int pageSize,
            UserVo userVo
    ){
        System.out.println("可视化接收的条件"+userVo+"PageNumber:"+pageNumber+"PageSize:"+pageSize);
        int pageNum = pageNumber;
        HttpSession session = request.getSession(); //拿去session中的用户信息
        if (session.getAttribute("user") instanceof Teacher){       //校验用户是否为教师
            Teacher tea = (Teacher) session.getAttribute("user");
            if ("2".equals(tea.getLevel())){            //查看管理员级别
                userVo.setClasses(tea.getDep());
                List<KSH> kshList = dataService.queryDateListKSH2(pageNum,30,userVo);  //查询数据库
                return kshList;
            }
        }
        //返回封装好的响应
        List<KSH> kshList = dataService.queryDateListKSH(pageNum,30,userVo);
        return kshList;
    }

    @RequestMapping("queryClasses")
    @ResponseBody
    public List<String> queryClasses(){
        List<String> classesList = dataService.queryClasses();
        return classesList;
    }

    @RequestMapping("queryStuData")
    @ResponseBody
    public PageVo<Student> queryStuData(@RequestParam(required = false,defaultValue = "1") int pageNumber,
                                      @RequestParam(required = false,defaultValue = "3") int pageSize,
                                      StuVO stuVO)
    {
        int pageNum = pageNumber;
        PageVo<Student> studentList = dataService.queryStuData(pageNum,pageSize,stuVO);
        return studentList;
    }

    //接收的请求头
    @RequestMapping("addData")
   //声明返回的格式为JSON
    @ResponseBody
    public String addData(HttpServletRequest request, User user){
        //拿取Session中的用户信息
        HttpSession session = request.getSession();
        Student stu =(Student) session.getAttribute("user");
        //返回拿取的信息，用拿取信息的主键去查询数据库
        Student stu1 = userService.checkNumber(stu.getNumber());
        //获取今天的日期String类型
        String today = DateUtil.nowDate();
        //判断这个人今天是否已经填报
        User checkuser = dataService.checkTianbao(stu1.getNumber(),today);
        System.out.println("今天的人：：："+checkuser);
        //此人今天未填报
        if(checkuser == null){
            //则对持久层的数据进行封装准备存储
            user.setNumber(stu1.getNumber());
            user.setName(stu1.getName());
            user.setAddress(stu1.getAddress());
            user.setDel(0);
            //进区时间类型转换
            if(user.getS_intime() != null){
                user.setIntime(DateUtil.string2Date(user.getS_intime()));
            }
            user.setIswailai(checkwailai(stu1,user));
            user.setZhuangtai(checkzhuangtai(user));
            //调用Dao层进行业务逻辑和持久化操作
            dataService.addData(user);
            return "success";
        }else{
            //此人今天已经填报
            return "fail";
        }
    }



    @RequestMapping("updateStu")
    @ResponseBody
    public String updateStu(Student stu){
        dataService.updateStu(stu);
        return "success";
    }


    @RequestMapping("delStu")
    @ResponseBody
    public String delStu(String number){
        dataService.delStu(number);
        return "success";
    }



    //根据现居住地判断是否外来
    private String checkwailai(Student stu1, User user) {
        if(stu1.getAddress().equals(user.getAddress_now())){
            return "本地";
        }else {
            return "外来";
        }
    }

    //根据温度判断状态
    public String checkzhuangtai(User user){
        if(user.getTemperature() <= 37.2){
            return "正常";
        }else if( user.getTemperature() <= 38.0 && user.getTemperature() > 37.2){
            return "低热";
        }else if(user.getTemperature() >= 38.0 ){
            return "高烧";
        }
        return "未知";
    }
}
