package com.travel.dx.godaxing.modules.me.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class IsMobileNo {
    /**
     * 判断是否是正确的手机号码
     *
     */
    public static boolean isMobileNo(String mobiles) {
        Pattern p = Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
