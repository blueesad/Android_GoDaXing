package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.YueJiCardInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class YueJiCardParse {
    public static YueJiCardInfo.DataBean YueJiCard(String s) {
        YueJiCardInfo.DataBean info = null;
        try {
            JSONObject object = new JSONObject(s);
            JSONObject subObject = object.getJSONObject("data");
            info = new YueJiCardInfo.DataBean();
            info.setId(subObject.getString("id"));
            info.setTitle(subObject.getString("title"));
            info.setContent(subObject.getString("content"));
            info.setLast_modify_time(subObject.getInt("last_modify_time"));
            info.setShare(subObject.getString("share"));
            info.setCollection(subObject.getString("collection"));

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return info;
    }

    public static List<YueJiCardInfo.DataBean.SightBean> YueJiCardSight(String s) {
        List<YueJiCardInfo.DataBean.SightBean> list = new ArrayList<>();
        YueJiCardInfo.DataBean.SightBean info = null;
        try {
            JSONObject object = new JSONObject(s);
            JSONObject dataObject = object.getJSONObject("data");
            JSONArray jsonArray = dataObject.getJSONArray("sight");
            for (int i = 0; i < jsonArray.length(); i++) {
                info = new YueJiCardInfo.DataBean.SightBean();
                JSONObject suObject = jsonArray.getJSONObject(i);
                info.setId(suObject.getString("id"));
                info.setName(suObject.getString("name"));
                info.setDistance(suObject.getDouble("distance"));
                info.setImg(suObject.getString("img"));
                info.setYuejika(suObject.getString("yuejika"));
                list.add(info);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
