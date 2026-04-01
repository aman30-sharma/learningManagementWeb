package com.edumatrix.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        e.printStackTrace();
        String errorMsg = e.getMessage() != null ? e.getMessage() : "Internal Server Error";
        model.addAttribute("error", errorMsg);
        return "error";
    }
}
