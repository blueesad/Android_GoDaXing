package com.travel.dx.godaxing.modules.me.util;


import com.travel.dx.godaxing.modules.me.bean.LoginInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginParse {
    private static JSONObject subJson;
    private static LoginInfo info;

    public static LoginInfo parseLoginmessage(String json) {
            try {
                subJson = new JSONObject(json);
                info = new LoginInfo();
                info.setMsg(subJson.getString("msg"));
                info.setToken(subJson.getString("token"));
                info.setStatus(subJson.getInt("status"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return  info;
    }
}
