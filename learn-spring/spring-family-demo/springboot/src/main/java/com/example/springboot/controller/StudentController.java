package com.example.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.springboot.model.Student;
import com.example.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wfh
 * @create 2023/2/28 17:17
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student")
    public @ResponseBody Object student(Integer id) {

        Student student = studentService.queryStudentById(id);

        return student;
    }

    @RequestMapping(value = "/student")
//    @ResponseBody
    public Object student() {
        Student student = new Student();
        student.setId(1001);
        student.setName("zhangsan");
        return student;
    }

    //该方法请求方式支持:GET和POST请求
    @RequestMapping(value = "/queryStudentById", method = {RequestMethod.GET, RequestMethod.POST})
    public Object queryStudentById(Integer id) {
        Student student = new Student();
        student.setId(id);
        return student;
    }


    //    @RequestMapping(value = "/queryStudentById2",method = RequestMethod.GET)
    @GetMapping(value = "/queryStudentById2") //相当于上一句话,只接收GET请求,如果请求方式不对会报405错误
    //该注解通过在查询数据的时候使用 -> 查询
    public Object queryStudentById2() {
        return "Ony GET Method";
    }

    //    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @PostMapping(value = "/insert") //相当于一句话
    //该注解通常在新增数据的时候使用 -> 新增
    public Object insert() {
        return "Insert success";
    }

    //    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/delete")//相当于上一句话
    //该注解通常在删除数据的时候使用 -> 删除
    public Object delete() {
        return "delete Student";
    }


    //    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @PutMapping(value = "/update") //相当于上一句话
    //该注解通常在修改数据的时候使用 -> 更新
    public Object update() {
        return "update student info1";
    }

    //---------------------------------------RESTful---------------------------------------------------------
    //    @RequestMapping(value = "/student/detail/{id}/{age}")
    @GetMapping(value = "/student/detail/{id}/{age}")
    public Object student1(@PathVariable("id") Integer id,
                           @PathVariable("age") Integer age) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put("id", id);
        retMap.put("age", age);
        return retMap;
    }

    //    @RequestMapping(value = "/student/detail/{id}/{status}")
    @DeleteMapping(value = "/student/detail/{id}/{status}")
    public Object student2(@PathVariable("id") Integer id,
                           @PathVariable("status") Integer status) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put("id", id);
        retMap.put("status", status);
        return retMap;
    }

    //以上代码student1和student2会出现请求路径冲突问题
    //通常在RESTful风格中方法的请求方式会按增删改查的请求方式来区分
    //修改请求路径
    //RESUful请求风格要求路径中使用的单词都是名称,最好不要出现动词

    @DeleteMapping(value = "/student/{id}/detail/{city}")
    public Object student3(@PathVariable("id") Integer id,
                           @PathVariable("city") Integer city) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put("id", id);
        retMap.put("city", city);
        return retMap;
    }

    @PostMapping(value = "/student/{id}")
    public String addStudent(@PathVariable("id") Integer id) {
        return "add student ID:" + id;
    }

    @PutMapping(value = "/student/{id}")
    public String updateStudent(@PathVariable("id") Integer id) {
        return "update Student ID:" + id;
    }

    //--------------------------------------------redis----------------------------------------------------------------
    //https://blog.csdn.net/weixin_47872288/article/details/118410080
    //https://blog.csdn.net/lydms/article/details/105224210?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522163452840716780274157276%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=163452840716780274157276&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-105224210.pc_search_result_control_group&utm_term=RedisTemplate&spm=1018.2226.3001.4187
    @RequestMapping(value = "/put")
    public @ResponseBody Object put(String key, String value) {

        studentService.put(key, value);

        return "值已成功放入redis";
    }

    @RequestMapping(value = "/get")
    public @ResponseBody String get() {
        String count = studentService.get("count");
        return "数据count为:" + count;
    }

    //----------------------------------------dubbo--------------------------------------------------------------------------
    //https://blog.csdn.net/weixin_47872288/article/details/119739231
    /**
     * 服务消费者：
     * 实现业务逻辑的一个层面
     * 此处比往常多了一个表示dubbo接口暴露的注解@Reference(interfaceClass = StudentService.class,version = "1.0.0",check = false)
     */
    //dubbo:reference interface="" version="" check=false
    @Reference(interfaceClass = StudentService.class, version = "1.0.0", check = false)
    private StudentService studentService2;

    @RequestMapping(value = "/student/count")
    @ResponseBody
    public Object studentCount() {

        Integer allStudentCount = studentService2.queryAllStudentCount();

        return "学生总人数为:" + allStudentCount;
    }
}

