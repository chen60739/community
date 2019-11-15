package com.example.demo.service;

import com.example.demo.dto.PageDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageDTO getList(Integer currentPage){
        List<QuestionDTO> list = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();
        List<Question> questions = questionMapper.selectAll(currentPage*5-5);
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        pageDTO.setData(list);
        pageDTO.setNum(questionMapper.count());
        return pageDTO;
    }

    public PageDTO myQuestion(Integer currentPage, User user) {
        List<QuestionDTO> list = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();
        List<Question> questions = questionMapper.selectByUser(user.getId(),currentPage*5-5);
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        pageDTO.setData(list);
        pageDTO.setNum(questions.size());
        return pageDTO;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.selectById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
