package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.YueJiCardDetailsInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class YueJiCardDetailsParse {
    public static YueJiCardDetailsInfo.DataBean YueJiCardDetails(String s) {
        YueJiCardDetailsInfo.DataBean rullBean=null;


        try {
            JSONObject object=new JSONObject(s);
            JSONObject dataObject=object.getJSONObject("data");
            rullBean = new YueJiCardDetailsInfo.DataBean();
            rullBean.setName(dataObject.getString("name"));
            rullBean.setYuejika(dataObject.getString("yuejika"));
            rullBean.setShare(dataObject.getString("share"));
            rullBean.setCollection(dataObject.getString("collection"));
            rullBean.setAddress(dataObject.getString("address"));
            rullBean.setContent(dataObject.getString("content"));
            rullBean.setPointout(dataObject.getString("pointout"));
            rullBean.setTraffic(dataObject.getString("traffic"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rullBean;
    }

}
