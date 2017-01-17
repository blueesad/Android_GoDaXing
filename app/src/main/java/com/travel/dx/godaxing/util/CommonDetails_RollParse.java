package com.travel.dx.godaxing.util;


import com.travel.dx.godaxing.bean.CommonDetailsInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommonDetails_RollParse {
    public static List<CommonDetailsInfo.DataBean.ImagesBean> parseShopDetails(String json) {
        List<CommonDetailsInfo.DataBean.ImagesBean> rullBeanList = new ArrayList<>();
        CommonDetailsInfo.DataBean.ImagesBean rullBean = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = jsonObject.getJSONObject("data");
            JSONArray images = jsonObject.getJSONArray("images");
            for (int i = 0; i < images.length(); i++) {
                rullBean = new CommonDetailsInfo.DataBean.ImagesBean();
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
