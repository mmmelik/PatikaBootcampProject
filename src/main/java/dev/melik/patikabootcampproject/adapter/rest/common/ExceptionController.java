package dev.melik.patikabootcampproject.adapter.rest.common;

import dev.melik.patikabootcampproject.domain.exception.PatikaDataNotFoundException;
import dev.melik.patikabootcampproject.domain.exception.PatikaUnauthorizedRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(PatikaDataNotFoundException.class)
    public ExceptionResponse handleAuthenticationException(PatikaDataNotFoundException e){
        return new ExceptionResponse(e.getExceptionType(),e.getDetail());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(PatikaUnauthorizedRequestException.class)
    public ExceptionResponse handleUnauthorizedRequest(PatikaUnauthorizedRequestException e){
        return new ExceptionResponse(e.getExceptionType(),e.getDetail());
    }

}
