package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.RoutesDetailsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class RoutesDetailsRullParse {
    public static List<RoutesDetailsInfo.DataBean.ImagesBean> RoutesDetails(String s) {
        List<RoutesDetailsInfo.DataBean.ImagesBean> rullList=new ArrayList<>();
        RoutesDetailsInfo.DataBean.ImagesBean rullBean=null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject dataObject=jsonObject.getJSONObject("data");
            JSONArray dataArray=dataObject.getJSONArray("images");
            for (int i = 0; i < dataArray.length(); i++) {
                rullBean = new RoutesDetailsInfo.DataBean.ImagesBean();
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
