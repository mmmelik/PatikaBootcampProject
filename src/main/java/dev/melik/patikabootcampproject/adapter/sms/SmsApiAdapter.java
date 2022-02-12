package dev.melik.patikabootcampproject.adapter.sms;

import dev.melik.patikabootcampproject.domain.port.api.SmsApiPort;
import org.springframework.stereotype.Service;

@Service
public class SmsApiAdapter implements SmsApiPort {

    @Override
    public boolean sendCreditApplicationSms(String phoneNumber, String message) {
        //TODO: Integrate sms api.
        return true;
    }
}
