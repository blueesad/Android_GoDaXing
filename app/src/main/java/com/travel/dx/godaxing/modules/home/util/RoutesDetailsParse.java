package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.RoutesDetailsInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class RoutesDetailsParse {
    public static RoutesDetailsInfo.DataBean RoutesDetails(String s) {
        RoutesDetailsInfo.DataBean rullBean=null;
        try {
            JSONObject object=new JSONObject(s);
            JSONObject dataObject=object.getJSONObject("data");
            rullBean = new RoutesDetailsInfo.DataBean();
            rullBean.setTitle(dataObject.getString("title"));
            rullBean.setContent(dataObject.getString("content"));
            rullBean.setPublish_time(dataObject.getString("publish_time"));
            rullBean.setShare(dataObject.getString("share"));
            rullBean.setCollection(dataObject.getString("collection"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rullBean;
    }
}
