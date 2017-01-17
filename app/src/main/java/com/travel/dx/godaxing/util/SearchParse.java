package com.travel.dx.godaxing.util;

import android.util.Log;

import com.travel.dx.godaxing.modules.near.bean.CommendInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */
public class SearchParse
{

    /**
     * 解析游戏列表的数据
     * 
     * @param json
     * @return
     */
    public static List<CommendInfo> parseSearchList(String json)
    {
        List<CommendInfo> list = new ArrayList<>();
        CommendInfo info = null;
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("data");
            Log.e("print", "parseSearchList: " +array.toString() );
            for (int i = 0; i < array.length(); i++)
            {
                try
                {
                    info = new CommendInfo();
                    JSONObject subJson = array.getJSONObject(i);
                    Log.i("info", "parseSearchList: "+subJson);
                    info.setId(subJson.getString("id"));
                    info.setName(subJson.getString("goodsname"));
                    info.setDistance(subJson.getDouble("distance"));
                    info.setIv(subJson.getString("img"));
                    info.setPrice(subJson.getString("price"));
                    info.setOriginalprice(subJson.getString("originalprice"));
                    info.setAddress(subJson.getString("name"));
                    list.add(info);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
