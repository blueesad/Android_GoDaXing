package com.travel.dx.godaxing.util;


import android.util.Log;

import com.travel.dx.godaxing.bean.CommonDetailsInfo;
import com.travel.dx.godaxing.i.DetailsType;

import org.json.JSONObject;

public class CommonDetails_DataParse {
    public static CommonDetailsInfo.DataBean parseShopDetails(int type, String json) {
        CommonDetailsInfo.DataBean dataBean = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.i("--------------",json);
            jsonObject = jsonObject.getJSONObject("data");

            dataBean = new CommonDetailsInfo.DataBean();

            if (type == DetailsType.TYPE_SIGHT){
                dataBean.setName(jsonObject.getString("name"));
                dataBean.setPrice(jsonObject.getInt("price") + "");
                dataBean.setContent(jsonObject.getString("content"));
                dataBean.setPointout(jsonObject.getString("pointout"));
                dataBean.setAddress(jsonObject.getString("address"));
                dataBean.setPhone(jsonObject.getString("phone"));
                dataBean.setNotice(jsonObject.getString("notice"));
                dataBean.setOriginalprice(jsonObject.getString("originalprice"));
                dataBean.setShowscore(jsonObject.getString("showscore"));
                dataBean.setCerate_time(jsonObject.getString("cerate_time"));
                dataBean.setPublish_time(jsonObject.getString("publish_time"));
                dataBean.setLast_modify_time(jsonObject.getString("last_modify_time"));
                dataBean.setShare(jsonObject.getString("share"));
                dataBean.setCollection(jsonObject.getString("collection"));
                dataBean.setGoodsdesc(jsonObject.getString("goodsdesc"));
                dataBean.setIsort(jsonObject.getString("isort"));
                dataBean.setDistance(jsonObject.getDouble("distance"));
                dataBean.setTraffic(jsonObject.getString("traffic"));
            }else if (type == DetailsType.TYPE_PLAY){
                dataBean.setName(jsonObject.getString("name"));
                dataBean.setSalecount(jsonObject.getString("salecount"));
                dataBean.setContent(jsonObject.getString("content"));
                dataBean.setPointout(jsonObject.getString("pointout"));
                dataBean.setAddress(jsonObject.getString("address"));
                dataBean.setPhone(jsonObject.getString("phone"));
                dataBean.setNotice(jsonObject.getString("notice"));
                dataBean.setCerate_time(jsonObject.getString("cerate_time"));
                dataBean.setPublish_time(jsonObject.getString("publish_time"));
                dataBean.setLast_modify_time(jsonObject.getString("last_modify_time"));
                dataBean.setShare(jsonObject.getString("share"));
                dataBean.setCollection(jsonObject.getString("collection"));
                dataBean.setGoodsdesc(jsonObject.getString("goodsdesc"));
                dataBean.setDistance(jsonObject.getDouble("distance"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataBean;
    }
}
