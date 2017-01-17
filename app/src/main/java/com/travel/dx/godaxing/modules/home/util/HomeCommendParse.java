package com.travel.dx.godaxing.modules.home.util;

import android.util.Log;

import com.travel.dx.godaxing.modules.home.bean.HomeDetailsInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
public class HomeCommendParse
{

    public static List<HomeDetailsInfo.DataBean> parseHomeDetails(String json) {
        HomeDetailsInfo.DataBean dataBean = null;
        List<HomeDetailsInfo.DataBean> homeBeanList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray dataObject=jsonObject.getJSONArray("data");
            for (int i=0;i<dataObject.length();i++) {
                dataBean = new  HomeDetailsInfo.DataBean();
                JSONObject homeJson=dataObject.getJSONObject(i);
                dataBean.setName(homeJson.getString("name"));
                dataBean.setPrice(homeJson.getString("price") + "");
                dataBean.setImg(homeJson.getString("img"));
                dataBean.setId(homeJson.getString("id"));
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
