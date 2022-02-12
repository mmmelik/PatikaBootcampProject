package dev.melik.patikabootcampproject.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    GENERIC_EXCEPTION(1, "Unknown Error"),

    USER_NOT_FOUND_EXCEPTION(1001,"User not found."),
    CREDIT_NOT_FOUND_EXCEPTION(1002,"Credit not found."),

    BAD_LOGIN_REQUEST_EXCEPTION(9001,"Invalid login request."),
    ILLEGAL_REQUEST_EXCEPTION(9002,"Unauthorized request."),
    USER_EXIST_EXCEPTION(9003,"User exist"),
    BAD_ID_NUMBER_EXCEPTION(9004,"Invalid TCKN.");

    private final Integer code;
    private final String message;
}
