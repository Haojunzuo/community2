package com.cjh.community2.controller;

import com.cjh.community2.dto.PaginationDTO;
import com.cjh.community2.model.User;
import com.cjh.community2.service.NotificationService;
import com.cjh.community2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    /*
    * 该方法执行之后进入的是我的问题界面或者是我的消息界面，根据HTML传来的action参数，判断进入哪个页面，如果进入问题页面，就会将分页的问题内容加入model，如果进入我的消息页面，就会将分页的消息的内容加入model。
    * 两者都进入profile页面，在该页面上进行判断显示。
    * */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") int page,
                          @RequestParam(name = "size",defaultValue = "2") int size){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination",paginationDTO);
        }else if("replies".equals(action)){
            PaginationDTO paginationDTO = notificationService.list(user.getId(),page,size);
            //unreadCount已经放在了session里面了，因此这里不再需要放入model。
//            Long unreadCount = notificationService.unreadCount(user.getId());
//            model.addAttribute("unreadCount",unreadCount);
            model.addAttribute("section","replies");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }

}
