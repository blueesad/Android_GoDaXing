package com.travel.dx.godaxing.modules.home.util;

import android.util.Log;

import com.travel.dx.godaxing.modules.home.bean.YueJiCardDetailsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class YueJiCardDetailsRollParse {
    public static List<YueJiCardDetailsInfo.DataBean.ImagesBean> YueJiCardDetails(String json) {
        List<YueJiCardDetailsInfo.DataBean.ImagesBean> rollList = new ArrayList<>();
        YueJiCardDetailsInfo.DataBean.ImagesBean rollBean = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray dataArray = dataObject.getJSONArray("images");
            Log.e("print", "YueJiCardDetails: "+dataArray );
            for (int i = 0; i < dataArray.length(); i++) {
                rollBean = new YueJiCardDetailsInfo.DataBean.ImagesBean();
                JSONObject subJson = dataArray.getJSONObject(i);
                Log.e("print", "YueJiCardDetails: "+subJson );
                rollBean.setImg_path(subJson.getString("img_path"));
                rollList.add(rollBean);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rollList;
    }
}
