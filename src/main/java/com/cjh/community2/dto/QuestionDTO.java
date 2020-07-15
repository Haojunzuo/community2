package com.cjh.community2.dto;

import com.cjh.community2.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long gmtcreate;
    private Long gmtmodified;
    private Long creator;
    private int commentcount;
    private int viewcount;
    private int likecount;
    private String tag;
    private User user;
}
