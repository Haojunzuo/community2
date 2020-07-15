package com.cjh.community2.service;

import com.cjh.community2.dto.CommentDTO;
import com.cjh.community2.enums.CommentTypeEnum;
import com.cjh.community2.exception.CustomizeErrorCode;
import com.cjh.community2.exception.CustomizeException;
import com.cjh.community2.mapper.CommentMapper;
import com.cjh.community2.mapper.QuestionExtMapper;
import com.cjh.community2.mapper.QuestionMapper;
import com.cjh.community2.mapper.UserMapper;
import com.cjh.community2.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentid() == null && comment.getParentid() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentid());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            Question question = questionMapper.selectByPrimaryKey(comment.getParentid());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentcount(1);
            questionExtMapper.incCommentCount(question);
        }
    }

    public List<CommentDTO> listByTargetId(Long questionid, CommentTypeEnum type){
        //根据question的id查询出所有的comment
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andParentidEqualTo(questionid)
                .andTypeEqualTo(type.getType());
        example.setOrderByClause("gmtcreate desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        //获取去重后的评论人的id
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);
        //获取评论人并转换为map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //把comment转换为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }

}
