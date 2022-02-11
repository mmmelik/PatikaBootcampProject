package dev.melik.patikabootcampproject.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class BaseException extends RuntimeException{
    private final ExceptionType exceptionType;
    private final String detail;
}
