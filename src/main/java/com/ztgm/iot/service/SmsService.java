package com.ztgm.iot.service;

import com.ztgm.iot.pojo.Sms;
import com.ztgm.iot.util.Message;


public interface SmsService {

    Message SendRegisterValidateCodeSms(String phone);

}
