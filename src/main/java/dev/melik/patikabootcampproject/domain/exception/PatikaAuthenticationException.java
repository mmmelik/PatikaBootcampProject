package dev.melik.patikabootcampproject.domain.exception;


public class PatikaAuthenticationException extends BaseException{

    public PatikaAuthenticationException(ExceptionType exceptionType, String detail) {
        super(exceptionType, detail);
    }
}
