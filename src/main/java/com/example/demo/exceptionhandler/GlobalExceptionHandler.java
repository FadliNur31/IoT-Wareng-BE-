package com.example.demo.exceptionhandler;

import com.example.demo.controller.BaseController;
import com.example.demo.helper.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(RuntimeException e) {
        return error(e.getMessage(),  HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse<Void>>handleException(ResourceNotFound e) {
        return error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }


}
