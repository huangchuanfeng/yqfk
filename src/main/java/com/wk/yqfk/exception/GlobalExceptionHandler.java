package com.wk.yqfk.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
   /* @ExceptionHandler(ParamException.class)
    public ModelAndView handlerParamException(ParamException e){
        log.error(e.getMessage(),e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("error",e.getMessage());
        mv.setViewName("car/add");
        return mv;
    }*/

    @ExceptionHandler(EtLoginException.class)
    public ModelAndView handleETLoginException(EtLoginException e) {
        log.error(e.getMessage(), e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e.getMessage());
        mv.setViewName("login");
        return mv;
    }

    @ExceptionHandler(NumberException.class)
    public ModelAndView handleNumberException(NumberException e) {
        log.error(e.getMessage(), e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("arror", e.getMessage());
        mv.setViewName("reg");
        return mv;
    }
}
