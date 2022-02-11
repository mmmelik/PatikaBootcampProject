package dev.melik.patikabootcampproject.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    GENERIC_EXCEPTION(1, "Unknown Error"),

    USER_NOT_FOUND_EXCEPTION(1001,"User not found."),
    CREDIT_NOT_FOUND_EXCEPTION(1002,"Credit not found."),

    BAD_LOGIN_REQUEST(9001,"Invalid login request."),
    ILLEGAL_REQUEST_EXCEPTION(9002,"Unauthorized request.");

    private final Integer code;
    private final String message;
}
