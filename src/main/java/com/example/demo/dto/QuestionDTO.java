package com.example.demo.dto;

import com.example.demo.model.Question;
import com.example.demo.model.User;
import lombok.Data;

@Data
public class QuestionDTO extends Question {
    private User user;
}
