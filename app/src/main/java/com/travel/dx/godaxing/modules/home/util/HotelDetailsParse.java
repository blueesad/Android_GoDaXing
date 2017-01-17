package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.HotelDetailsInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class HotelDetailsParse {
    public static HotelDetailsInfo.DataBean HotelDetails(String s) {
        HotelDetailsInfo.DataBean rullBean=null;


        try {
            JSONObject object=new JSONObject(s);
            JSONObject dataObject=object.getJSONObject("data");
            rullBean = new HotelDetailsInfo.DataBean();
            rullBean.setName(dataObject.getString("name"));
            rullBean.setAddress(dataObject.getString("address"));
            rullBean.setPhone(dataObject.getString("phone"));
            rullBean.setContent(dataObject.getString("content"));
            rullBean.setTraffic(dataObject.getString("traffic"));
            rullBean.setShare(dataObject.getString("share"));
            rullBean.setCollection(dataObject.getString("collection"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rullBean;
    }

}
