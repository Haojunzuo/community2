package com.cjh.community2.interceptor;

import com.cjh.community2.entity.User;
import com.cjh.community2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
//                System.out.println(token);
                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user",user);
                    break;
                }

            }

        }
        return true;
    }
}
