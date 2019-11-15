package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO {
    private List<QuestionDTO> data;
    private Integer currentPage = 0;
    private Integer totalPage;
    private Integer size = 5;

    public void setNum(Integer count){
        totalPage = count%5==0?count/5:count/5+1;
    }
}
