package com.cjh.community2.advice;

import com.alibaba.fastjson.JSON;
import com.cjh.community2.dto.ResultDTO;
import com.cjh.community2.exception.CustomizeErrorCode;
import com.cjh.community2.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request,Throwable e,Model model, HttpServletResponse response) {
        //得到contentType 的类型除了这里之外的其他的地方都不是用的application/json 因此这里用application/json来判断是否是评论的异常
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO;
            //返回json
            //如果抛出的异常是CustomizeException，使用CustomizeException 将CustomizeException中的指定的内容转换为ResultDTO对象
            if (e instanceof CustomizeException){
                resultDTO =ResultDTO.errorOf((CustomizeException) e);
            }else{
                //否则为系统异常，使用errorof将CustomizeErrorCode.SYS_ERROR转变为ResultDTO对象
                resultDTO =ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            //将resultDTO对象通过PrintWriter以json的方式写入到页面。
            try{
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.setStatus(200);
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSON.toJSONString(resultDTO));
            }catch (Exception e1){

            }
            return null;
        }else{
            //contentType不是application/json的情况
            if (e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

}
