package com.duyhk.apiwebbh.exception;

import com.duyhk.apiwebbh.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseDTO<String> handleRuntimeException(RuntimeException e){
        return ResponseDTO.<String>builder()
                .status(500)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseDTO<String> handleRuntimeException(MethodArgumentNotValidException e){
        return ResponseDTO.<String>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getFieldError().getDefaultMessage())
                .build();
    }
}
