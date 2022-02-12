package dev.melik.patikabootcampproject.domain.port.api;

import dev.melik.patikabootcampproject.domain.credit.Credit;

public interface SmsApiPort {
    boolean sendCreditApplicationSms(String phoneNumber, String message);
}
