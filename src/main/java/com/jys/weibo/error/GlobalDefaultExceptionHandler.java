package com.jys.weibo.error;

import com.jys.weibo.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.jys.weibo",})
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    //如果返回的为json数据或其他对象，就添加该注解
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception{
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(req.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);
        return errorInfo;
    }
}
