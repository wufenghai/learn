package com.example.springboot.controller;

import com.example.springboot.model.Abc;
import com.example.springboot.model.School;
import com.example.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wfh
 * @create 2023/2/28 16:23
 */
@Controller
public class IndexController {
    @Value("${school.name}")
    private String schoolName;

    @Value("${websit}")
    private String websit;

    @Autowired
    private School school;

    @Autowired
    private Abc abc;

    @RequestMapping(value = "/springboot/say")
    @ResponseBody
    public String say(@RequestParam String message) {
        return "Hello,SpringBoot! " + message + " " + schoolName + " " + websit;
    }

    @RequestMapping(value = "/mapvalue")
    @ResponseBody
    public Map<String, Object> mapvalue() {
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("message", "SpringBoot Project");
        return retMap;
    }

    @RequestMapping(value = "/school")
    public @ResponseBody String say() {
        return "school.name=" + school.getName() + "-----school.websit=" + school.getWebsit() + "====abc.name" + abc.getName() + "===abc.websit=" + abc.getWebsit();
    }

    @RequestMapping(value = "/say1")
    public ModelAndView say1() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Hello,SpringBoot");
        mv.setViewName("say");
        return mv;
    }

    //也可以
    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("message", "HelloWorld");
        return "say";
    }

    @RequestMapping(value = "/index1")
    public String index1(Model model) {
        model.addAttribute("data", "SpringBoot Thymeleaf");
        return "index";
    }

    @RequestMapping(value = "/index1")
    public ModelAndView index1() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("data", "SpringBoot");
        return mv;
    }

    //放入对象
    @RequestMapping(value = "/user/detail")
    public ModelAndView userDetail() {
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setId("1001");
        user.setAge("23");
        user.setUsername("lisi");

        mv.setViewName("userDetail");
        mv.addObject("user", user);

        return mv;
    }

}
