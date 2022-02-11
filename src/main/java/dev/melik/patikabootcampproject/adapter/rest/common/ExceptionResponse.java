package dev.melik.patikabootcampproject.adapter.rest.common;

import dev.melik.patikabootcampproject.domain.exception.ExceptionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

    private Integer code;
    private String message;
    private String detail;

    public ExceptionResponse(ExceptionType exceptionType, String detail) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
        this.detail = detail;
    }
}
