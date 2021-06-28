package me.warriorg.spring.mvc.exception.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="参数异常")
public class ArgumentException extends RuntimeException {

    public ArgumentException(String msg) {
        super(msg);
    }
}
