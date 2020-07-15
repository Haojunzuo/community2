package com.cjh.community2.dto;

import com.cjh.community2.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentid;
    private Long commentator;
    private String content;
    private Integer type;
    private Long gmtcreate;
    private Long gmtmodified;
    private Long likecount;
    private User user;
}
