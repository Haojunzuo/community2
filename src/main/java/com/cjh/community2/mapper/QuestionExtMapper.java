package com.cjh.community2.mapper;

import com.cjh.community2.model.Question;

import java.util.List;


public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question question);

    List<Question> selectRelated(Question question);
}