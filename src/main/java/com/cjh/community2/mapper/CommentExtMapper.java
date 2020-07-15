package com.cjh.community2.mapper;

import com.cjh.community2.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}