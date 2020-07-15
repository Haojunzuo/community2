package com.cjh.community2.controller;

import com.cjh.community2.dto.NotificationDTO;
import com.cjh.community2.dto.PaginationDTO;
import com.cjh.community2.enums.NotificationTypeEnum;
import com.cjh.community2.mapper.NotificationMapper;
import com.cjh.community2.model.User;
import com.cjh.community2.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
//    点击通知页面的通知题目，就会执行以下方法，会跳转到/question/id  url。
//    这种注解可以获得url上的内容这个内容到现在为止都是用thymeleaf语法添加到href中的。
    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
//        这里的read方法是将消息的状态变更为已读，返回的是notificationDTO,notificationDTO的getType方法获取的实际上是notification的type  1或者2
//        outerid实际上是发出的评论的id，如果type的值是1或者2，则跳转到   /question/id   url。 无论是question还是comment都是跳转到该url进行展示。
//        Notification  NotificationDTO中的outerid实际上都是问题的id，之前理解成如果通知发生在问题是问题，发生在评论上是评论，是不对的。
        NotificationDTO notificationDTO =  notificationService.read(id,user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType()==notificationDTO.getType()
        ||NotificationTypeEnum.REPLY_QUESTION.getType()==notificationDTO.getType()
        ){
            return "redirect:/question/"+notificationDTO.getOuterid();
        }else {
            return "redirect:/";
        }

    }
}
