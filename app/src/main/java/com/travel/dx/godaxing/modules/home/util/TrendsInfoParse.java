package com.travel.dx.godaxing.modules.home.util;

import android.util.Log;

import com.travel.dx.godaxing.modules.home.bean.TrendsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class TrendsInfoParse {
    public static List<TrendsInfo> parseTrendsDetails(String json) {
        TrendsInfo dataBean=null;
        List<TrendsInfo> listBean=new ArrayList<>();

        try {
            JSONObject jsonObject=new JSONObject(json);
            Log.i("--------------",json);
            JSONArray  dataArray=jsonObject.getJSONArray("data");
            Log.i("------------", dataArray.toString());
            for (int i=0;i<dataArray.length();i++){
                dataBean=new TrendsInfo();
                JSONObject infoJson=dataArray.getJSONObject(i);
                dataBean.setTitle(infoJson.getString("title"));
                dataBean.setImg(infoJson.getString("img"));
                dataBean.setId(infoJson.getString("id"));
                dataBean.setSummary(infoJson.getString("summary"));
                dataBean.setPublish(infoJson.getString("publish"));
               // dataBean.setCreat(infoJson.getString("creat"));
                listBean.add(dataBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listBean;
    }

}
