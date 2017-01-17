package com.travel.dx.godaxing.modules.home.util;

import android.util.Log;

import com.travel.dx.godaxing.modules.home.bean.ScenceInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class ScenceParse {
    public static List<ScenceInfo> parseScenceDetails(String json) {
       ScenceInfo dataBean = null;
        List<ScenceInfo> homeBeanList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.i("--------------",json);
            JSONArray infoJson=jsonObject.getJSONArray("data");
            Log.i("------------", infoJson.toString());
            for (int i=0;i<infoJson.length();i++) {
                dataBean = new  ScenceInfo();
                JSONObject homeJson=infoJson.getJSONObject(i);
                dataBean.setId(homeJson.getString("id"));
                dataBean.setName(homeJson.getString("name"));
                dataBean.setId(homeJson.getString("id"));
                dataBean.setPrice(homeJson.getString("price") + "");
                dataBean.setImg(homeJson.getString("img"));
                dataBean.setOriginalprice(homeJson.getString("originalprice") + "");
                dataBean.setGoodsdesc(homeJson.getString("goodsdesc"));
                dataBean.setDistance(Double.parseDouble(homeJson.getString("distance")));
                homeBeanList.add(dataBean);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return homeBeanList;
    }
}
