package com.cjh.community2.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long parentid;
    private String content;
    private Integer type;

}
