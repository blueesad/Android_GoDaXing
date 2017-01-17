package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.HomeRollData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class HomeRollDetailsRullParse {
    public static List<HomeRollData.DataEntity.AdvEntity> RoutesDetails(String s) {
        List<HomeRollData.DataEntity.AdvEntity> rullList = new ArrayList<>();
        HomeRollData.DataEntity.AdvEntity rullBean = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray dataArray = dataObject.getJSONArray("adv");
            for (int i = 0; i < dataArray.length(); i++) {
                rullBean = new HomeRollData.DataEntity.AdvEntity();
                JSONObject subJson = dataArray.getJSONObject(i);
                rullBean.setTid(subJson.getString("tid"));
                rullBean.setImg(subJson.getString("img"));
                rullList.add(rullBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rullList;
    }
}
