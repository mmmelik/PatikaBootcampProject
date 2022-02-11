package dev.melik.patikabootcampproject.domain.exception;

public class PatikaDataNotFoundException extends BaseException{
    public PatikaDataNotFoundException(ExceptionType exceptionType, String detail) {
        super(exceptionType, detail);
    }
}
