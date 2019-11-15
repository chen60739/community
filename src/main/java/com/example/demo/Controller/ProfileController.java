package com.example.demo.Controller;

import com.example.demo.dto.PageDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }

        if ("question".equals(action)){
            model.addAttribute("title","我的问题");
            model.addAttribute("setion","question");
        }
        if ("replie".equals(action)){
            model.addAttribute("title","最新回复");
            model.addAttribute("setion","replie");
        }
        return "profile";
    }

    @GetMapping("/profile/getData")
    @ResponseBody
    public PageDTO getData(HttpServletRequest request, @RequestParam("currentPage") Integer currentPage) {
        User user = (User) request.getSession().getAttribute("user");
        PageDTO pageDTO = questionService.myQuestion(currentPage,user);
        return pageDTO;
    }
}
