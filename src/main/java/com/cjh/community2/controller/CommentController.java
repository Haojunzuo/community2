package com.cjh.community2.controller;

import com.cjh.community2.dto.CommentCreateDTO;
import com.cjh.community2.dto.CommentDTO;
import com.cjh.community2.dto.ResultDTO;
import com.cjh.community2.enums.CommentTypeEnum;
import com.cjh.community2.exception.CustomizeErrorCode;
import com.cjh.community2.model.Comment;
import com.cjh.community2.model.User;
import com.cjh.community2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentid(commentCreateDTO.getParentid());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtcreate(System.currentTimeMillis());
        comment.setGmtmodified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikecount(0L);
        commentService.insert(comment,user);
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
