package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.HotelDetailsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class YueJiCardRollParse {
    public static List<HotelDetailsInfo.DataBean.ImagesBean> HotelDetails(String json) {
       List<HotelDetailsInfo.DataBean.ImagesBean> rullList=new ArrayList<>();
        HotelDetailsInfo.DataBean.ImagesBean rullBean=null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObject=jsonObject.getJSONObject("data");
            JSONArray  dataArray=dataObject.getJSONArray("images");
            for (int i = 0; i < dataArray.length(); i++) {
                rullBean = new HotelDetailsInfo.DataBean.ImagesBean();
                JSONObject subJson = dataArray.getJSONObject(i);
                rullBean.setImg_path(subJson.getString("img_path"));
                rullList.add(rullBean);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  rullList;
    }
}
