package com.travel.dx.godaxing.modules.me.util;


import android.util.Log;

import com.travel.dx.godaxing.DaXingApplication;
import com.travel.dx.godaxing.modules.me.bean.MeansInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class MeansParse {
    public static MeansInfo parseMeansmessage(String json) {

        MeansInfo meansInfo= new MeansInfo();


            try {
                JSONObject jsonObject = new JSONObject(json);


//                "data": {
//                    "mobile": "18039002582",
//                            "nickname": "sfasdfsf",
//                            "email": null,
//                            "img": "http:\/\/dxlv.iyoudaxing.com",
//                            "score": "0",
//                            "sex": "1",
//                            "ind_signature": "qwwf"
//                },
//                Suspicious size: this will make the view invisible, probably intended for layout_height

                JSONObject dataJson=jsonObject.getJSONObject("data");

                    meansInfo.setMobile(dataJson.getString("mobile"));
                    meansInfo.setNickname(dataJson.getString("nickname"));
                    meansInfo.setEmail(dataJson.getString("email"));
                    meansInfo.setSex(dataJson.getString("sex"));
                    meansInfo.setInd_signature(dataJson.getString("ind_signature"));
                    meansInfo.setMsg(dataJson.getString("msg"));
                  DaXingApplication.token=dataJson.getString("token");


                Log.i("parsemeansmessage: ", dataJson.toString());


                Log.i("parsemeansmessage: ", DaXingApplication.token);
            } catch (JSONException e) {
                e.printStackTrace();
            }




        return  meansInfo;
    }
}
