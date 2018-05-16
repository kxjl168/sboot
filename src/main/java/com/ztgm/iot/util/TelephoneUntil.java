package com.ztgm.iot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TelephoneUntil {

    //验证是否是手机号
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((1[0-9])|(1[^4,\\D])|(1[0,5-9]))\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}
