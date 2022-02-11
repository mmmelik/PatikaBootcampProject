package dev.melik.patikabootcampproject.domain.exception;

public class PatikaUnauthorizedRequestException extends BaseException{

    public PatikaUnauthorizedRequestException(ExceptionType exceptionType, String detail) {
        super(exceptionType, detail);
    }
}
