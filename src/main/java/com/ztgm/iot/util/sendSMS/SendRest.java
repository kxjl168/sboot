package com.ztgm.iot.util.sendSMS;


import com.ztgm.iot.util.sendSMS.client.AbsRestClient;
import com.ztgm.iot.util.sendSMS.client.JsonReqClient;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class SendRest {

    private final static Logger logger = LoggerFactory.getLogger(SendRest.class);

    private static final String sid = "f215753a9a3a83541468299682de951e";
    private static final String token = "12399bbaa028e96e541bdf39cab1f54f";
    private static final String appid = "59035e326f4c47919c34615f69e1629c";
    private static final String templateid = "245460";
    private static final String register_validate_code_templateid = "245460";

    static AbsRestClient InstantiationRestAPI() {
        return new JsonReqClient();
    }

    public static Boolean SendRegisterValidateCodeSms(String param, String mobile, String uid) {
        try {
            String result = InstantiationRestAPI().sendSms(sid, token, appid, register_validate_code_templateid, param, mobile, uid);
            String code = JSONObject.fromObject(result).getString("code");
            logger.info("Response content is: " + result);
            return "000000".equals(code) || Objects.equals("000000", code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Boolean SendSms(String param, String mobile, String uid) {
        try {
            String result = InstantiationRestAPI().sendSms(sid, token, appid, templateid, param, mobile, uid);
            String code = JSONObject.fromObject(result).getString("code");
            logger.info("Response content is: " + result);
            return "000000".equals(code) || Objects.equals("000000", code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Boolean SendSmsBatch(String param, String mobile, String uid) {
        try {
            String result = InstantiationRestAPI().sendSmsBatch(sid, token, appid, templateid, param, mobile, uid);
            String code = JSONObject.fromObject(result).getString("code");
            logger.info("Response content is: " + result);
            return "000000".equals(code) || Objects.equals("000000", code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
