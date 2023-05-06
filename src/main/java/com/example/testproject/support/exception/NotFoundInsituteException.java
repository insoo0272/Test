package com.example.testproject.support.exception;

import org.springframework.http.HttpStatus;

public class NotFoundInsituteException extends BaseException{
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getDescription() {
        return "찾을 수 없는 기관입니다. ";
    }

}
