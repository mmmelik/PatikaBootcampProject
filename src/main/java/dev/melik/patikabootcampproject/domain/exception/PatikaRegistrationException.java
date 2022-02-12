package dev.melik.patikabootcampproject.domain.exception;

public class PatikaRegistrationException extends BaseException {

    public PatikaRegistrationException(ExceptionType exceptionType, String detail) {
        super(exceptionType, detail);
    }
}
