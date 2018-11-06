package com.wgq.aop;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public String argumentValidHandler(MethodArgumentNotValidException e){
        String msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return msg;
    }

}
