package com.travel.dx.godaxing.modules.home.util;

import android.util.Log;

import com.travel.dx.godaxing.modules.home.bean.HotelInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class HotelInfoParse {
    public static List<HotelInfo> parseHotelDetails(String json) {
        HotelInfo dataBean=null;
        List<HotelInfo> listBean=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            Log.i("--------------",json);
            JSONArray dataArray=jsonObject.getJSONArray("data");
            Log.i("------------", dataArray.toString());
            for (int i=0;i<dataArray.length();i++){
                dataBean=new HotelInfo();
                JSONObject infoJson=dataArray.getJSONObject(i);
                dataBean.setName(infoJson.getString("name"));
                dataBean.setImg(infoJson.getString("img"));
                dataBean.setId(infoJson.getString("id"));
                dataBean.setPhone(infoJson.getString("phone"));
                listBean.add(dataBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listBean;
    }

}
