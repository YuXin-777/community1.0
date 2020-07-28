package com.xinyu.friday.controller.interceptor;

import com.xinyu.friday.entity.LoginTicket;
import com.xinyu.friday.entity.User;
import com.xinyu.friday.service.UserService;
import com.xinyu.friday.util.CookieUtil;
import com.xinyu.friday.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtil.getValue(request, "ticket");
        if(ticket != null){
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            if(loginTicket.getStatus() == 0 && loginTicket != null && loginTicket.getExpired().after(new Date())){
                User user = userService.findUserById(loginTicket.getId());
                hostHolder.setUser(user);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if(user != null && modelAndView!=null){
            modelAndView.addObject("loginUser",user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
