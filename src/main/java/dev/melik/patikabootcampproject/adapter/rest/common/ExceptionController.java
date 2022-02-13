package dev.melik.patikabootcampproject.adapter.rest.common;

import dev.melik.patikabootcampproject.domain.exception.ExceptionType;
import dev.melik.patikabootcampproject.domain.exception.PatikaDataNotFoundException;
import dev.melik.patikabootcampproject.domain.exception.PatikaRegistrationException;
import dev.melik.patikabootcampproject.domain.exception.PatikaUnauthorizedRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PatikaRegistrationException.class)
    public ExceptionResponse handleRegistrationException(PatikaRegistrationException e){
        return new ExceptionResponse(e.getExceptionType(),e.getDetail());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleRegistrationException(Exception e){
        log.error(e.toString(),e);
        return new ExceptionResponse(ExceptionType.GENERIC_EXCEPTION,e.getMessage());
    }



}
