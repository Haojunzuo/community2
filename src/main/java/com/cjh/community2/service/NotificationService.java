package com.cjh.community2.service;

import com.cjh.community2.dto.NotificationDTO;
import com.cjh.community2.dto.PaginationDTO;
import com.cjh.community2.dto.QuestionDTO;
import com.cjh.community2.enums.NotificationStatusEnum;
import com.cjh.community2.enums.NotificationTypeEnum;
import com.cjh.community2.exception.CustomizeErrorCode;
import com.cjh.community2.exception.CustomizeException;
import com.cjh.community2.mapper.NotificationMapper;
import com.cjh.community2.mapper.UserMapper;
import com.cjh.community2.model.*;
import okhttp3.internal.http2.ErrorCode;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Long id, int page, int size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id);
        int totalCount = (int) notificationMapper.countByExample(notificationExample);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (totalPage == 0) {
            page = 1;
        } else {
            if (page < 1) {
                page = 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
        }
        paginationDTO.setPagination(totalPage, page);
        int offset = size * (page - 1);
        //查询出该用户所有的信息，并按照提出时间倒序排列，并分页。
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id);
        example.setOrderByClause("gmtcreate desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        if (notifications.size() == 0) {
            return paginationDTO;
        }

//        Set<Long> disUserIds = notifications.stream().map(notify -> notify.getNotifier()).collect(Collectors.toSet());
//        ArrayList<Long> userIds = new ArrayList<>(disUserIds);
//        UserExample userExample = new UserExample();
//        userExample.createCriteria().andIdIn(userIds);
//        List<User> users = userMapper.selectByExample(userExample);
//        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(u -> u.getId(), u -> u));

        //定义一个List<NotificationDTO>类型的notificationDTOS,用来存储每一个notificationDTO，这里的notificationDTO是由notification增加了typeName之后产生的。
        //NotificationDTO是Notification的数据传输对象，但是前者仅仅比后者多了一个typeName属性。
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    //    根据userid，status查出该用户没有查看的消息的个数，也就是status为0的消息的个数。
    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    //    当查看消息，进入消息界面的时候，需要将该消息的status列设为1，表示已读。返回的是NotificationDTO对象，该对象根据notification的type的值是1或2 设置typeName为
    //    “回复了问题”或者“回复了评论”
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}

