package com.cjh.community2.controller;

import com.cjh.community2.cache.TagCache;
import com.cjh.community2.dto.QuestionDTO;
import com.cjh.community2.model.Question;
import com.cjh.community2.model.User;
import com.cjh.community2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.thymeleaf.util.StringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());

        return "publish";
    }
    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
//        System.out.println(TagCache.get());
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            @RequestParam(value = "id",required = false) Long id,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("tags", TagCache.get());
        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","输入非法标签"+invalid);
            return "publish";
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
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";//redirect重定向的是路由地址。而不是template里面的模板。
    }
}
