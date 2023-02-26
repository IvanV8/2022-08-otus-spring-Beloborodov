package ru.otus.spring082022.homework13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.otus.spring082022.homework13.service.ServiceException;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<InternalErrorResponse> handleInternalServerError(RuntimeException ex, WebRequest request) {
        InternalErrorResponse exceptionResponse = new InternalErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Custom internal server exception raised");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public static class InternalErrorResponse {
        private int code;
        private String msg;

        public InternalErrorResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }
    }
}
