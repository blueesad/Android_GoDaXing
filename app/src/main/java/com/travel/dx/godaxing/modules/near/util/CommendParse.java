package com.travel.dx.godaxing.modules.near.util;

import com.travel.dx.godaxing.modules.near.bean.CommendInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CommendParse
{

    /**
     * 解析游戏列表的数据
     * 
     * @param json
     * @return
     */
    public static List<CommendInfo> parseCommendList(String json)
    {
        List<CommendInfo> list = new ArrayList<>();
        CommendInfo info = null;
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("data");
            for (int i = 0; i < array.length(); i++)
            {
                try
                {
                    info = new CommendInfo();
                    JSONObject subJson = array.getJSONObject(i);
                    info.setId(subJson.getString("id"));
                    info.setName(subJson.getString("name"));
                    info.setDistance(subJson.getDouble("distance"));
                    info.setIv(subJson.getString("img"));
                    info.setPrice(subJson.getString("price"));
                    info.setOriginalprice(subJson.getString("originalprice"));
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
