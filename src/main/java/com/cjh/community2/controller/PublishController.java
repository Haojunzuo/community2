package com.cjh.community2.controller;

import com.cjh.community2.entity.Question;
import com.cjh.community2.entity.User;
import com.cjh.community2.mapper.QuestionMapper;
import com.cjh.community2.mapper.UserMapper;
import com.cjh.community2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/publish1")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model){
        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
        }
        if(title==null||description==""){
            model.addAttribute("error","内容不能为空");
        }
        if(title==null||tag==""){
            model.addAttribute("error","标签不能为空");
        }
        model.addAttribute("title" ,title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtcreate(System.currentTimeMillis());
        question.setGmtmodified(question.getGmtcreate());
        questionMapper.create(question);
        return "redirect:/";//redirect重定向的是路由地址。而不是template里面的模板。
    }
}
