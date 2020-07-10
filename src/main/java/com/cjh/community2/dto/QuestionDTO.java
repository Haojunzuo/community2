package com.cjh.community2.dto;

import com.cjh.community2.entity.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String description;
    private long gmtcreate;
    private long gmtmodified;
    private int creator;
    private int commentcount;
    private int viewcount;
    private int likecount;
    private String tag;
    private User user;
}
