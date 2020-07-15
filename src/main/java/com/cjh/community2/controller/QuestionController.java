package com.cjh.community2.controller;

import com.cjh.community2.dto.CommentDTO;
import com.cjh.community2.dto.QuestionDTO;
import com.cjh.community2.enums.CommentTypeEnum;
import com.cjh.community2.service.CommentService;
import com.cjh.community2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        System.out.println(relatedQuestions.get(0));
        System.out.println(relatedQuestions.get(0).getTag());
        questionService.incView(id);
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentDTOS);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
