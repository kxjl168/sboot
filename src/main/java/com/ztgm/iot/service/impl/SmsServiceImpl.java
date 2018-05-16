package com.ztgm.iot.service.impl;


import com.ztgm.iot.dao.SmsMapper;
import com.ztgm.iot.pojo.Sms;
import com.ztgm.iot.service.SmsService;
import com.ztgm.iot.util.Message;
import com.ztgm.iot.util.TelephoneUntil;
import com.ztgm.iot.util.UUIDUtil;
import com.ztgm.iot.util.sendSMS.SendRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

@Transactional
@Service
public class SmsServiceImpl implements SmsService {


    @Autowired
    private SmsMapper smsMapper;

    ReentrantLock lock = new ReentrantLock();


    /**
     * 发送短信
     */
    @Override
    public Message SendRegisterValidateCodeSms(String phone) {

        Message msg = new Message();
        String mobile = phone;
        lock.lock();
        try {
            /* 验证手机号码格式 */
            if (!TelephoneUntil.isMobileNO(mobile)) {
                msg.setBol(false);
                msg.setMessage("手机号码不正确");
                return msg;
            }

            Random random = new Random();
            Integer validateCode = random.nextInt(899999);
            validateCode = validateCode + 100000;

            //发送短信
            Boolean bol = SendRest.SendRegisterValidateCodeSms(String.valueOf(validateCode), mobile, "0");
            if (bol) {
                Sms sms = new Sms();
                sms.setId(UUIDUtil.getUUID());
                sms.setTelephone(phone);
                sms.setMsg(String.valueOf(validateCode));
                sms.setType(1);
                sms.setTime(new Date());
                smsMapper.insert(sms);
                msg.setBol(true);
                msg.setMessage("发送成功");
                return msg;
            }
        } catch (Exception ioe) {
            msg.setBol(false);
            msg.setMessage("发送失败");
            ioe.printStackTrace();
        } finally {
            lock.unlock();
        }
        return msg;
    }


    /**
     * 获取手机最近验证码
     *
     * @param phone
     * @return
     */
    public Sms findValidateCodeSmsByPhone(String phone) {
        List<Sms> smsList = smsMapper.findValidateCodeSmsByPhone(phone);
        if (smsList == null || smsList.size() == 0) return null;
        return smsList.get(0);
    }


}
