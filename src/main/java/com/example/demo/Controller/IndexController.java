package com.example.demo.Controller;

import com.example.demo.dto.PageDTO;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        return "index";
    }

    @GetMapping("/getData")
    @ResponseBody
    public PageDTO getData(HttpServletRequest request, @RequestParam("currentPage") Integer currentPage) {
        PageDTO pageDTO = questionService.getList(currentPage);
        return pageDTO;
    }
}


