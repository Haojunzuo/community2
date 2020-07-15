package com.cjh.community2.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;

    private Long gmtcreate;

    private Integer status;

    private Long notifier;

    private String NOTIFIER_NAME;

    private String OUTER_TITLE;
    private Long outerid;

    private String typeName;
    private Integer type;
}
