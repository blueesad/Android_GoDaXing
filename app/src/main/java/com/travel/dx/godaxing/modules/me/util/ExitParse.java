package com.travel.dx.godaxing.modules.me.util;


import android.util.Log;

import com.travel.dx.godaxing.modules.me.bean.LoginInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class ExitParse {
    public static LoginInfo parseExitmessage(String json) {

        LoginInfo exitBean = new LoginInfo();


            try {
                JSONObject jsonObject = new JSONObject(json);
                String msg=jsonObject.getString("msg");
                exitBean.setMsg(msg);

            } catch (JSONException e) {
                e.printStackTrace();
            }




        return  exitBean;
    }
}
