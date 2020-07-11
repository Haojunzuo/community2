package com.cjh.community2.interceptor;

import com.cjh.community2.mapper.UserMapper;
import com.cjh.community2.model.User;
import com.cjh.community2.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class SessionInterceptor  implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                String token = cookie.getValue();
                UserExample userExample = new UserExample();
                userExample.createCriteria()
                        .andTokenEqualTo(token);
                List<User> users = userMapper.selectByExample(userExample);
                if (users.size()!=0){
                    request.getSession().setAttribute("user",users.get(0));
                    break;
                }
            }

        }
        return true;
    }
}
