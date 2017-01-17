package com.travel.dx.godaxing.modules.near.util;


import com.travel.dx.godaxing.modules.near.bean.CommendItemInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommendItemRullParse {
    public static List<CommendItemInfo.DataBean.ImagesBean> parseCommendItem(String json) {
        List<CommendItemInfo.DataBean.ImagesBean> rullBeanList = new ArrayList<>();
        CommendItemInfo.DataBean.ImagesBean rullBean = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = jsonObject.getJSONObject("data");
            JSONArray images = jsonObject.getJSONArray("images");
            for (int i = 0; i < images.length(); i++) {
                rullBean = new CommendItemInfo.DataBean.ImagesBean();
                JSONObject subJson = images.getJSONObject(i);
                rullBean.setImg_path(subJson.getString("img_path"));
                rullBeanList.add(rullBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rullBeanList;
    }
}
