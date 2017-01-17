package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.TrendsDetailsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class TrendsDetailsRullParse {
    public static List<TrendsDetailsInfo.DataBean.ImagesBean> TrendsDetails(String s) {
        List<TrendsDetailsInfo.DataBean.ImagesBean> rullList=new ArrayList<>();
        TrendsDetailsInfo.DataBean.ImagesBean rullBean=null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject dataObject=jsonObject.getJSONObject("data");
            JSONArray dataArray=dataObject.getJSONArray("images");
            for (int i = 0; i < dataArray.length(); i++) {
                rullBean = new TrendsDetailsInfo.DataBean.ImagesBean();
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
