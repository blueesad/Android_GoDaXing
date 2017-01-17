package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.PlayTogeInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class PlaytogetherParse {
    public static List<PlayTogeInfo> parseplayList(String json)
    {
        List<PlayTogeInfo> list = new ArrayList<>();
        PlayTogeInfo info = null;
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject datajson = jsonObject.getJSONObject("data");
            JSONArray array=datajson.getJSONArray("play");
            for (int i = 0; i < array.length(); i++)
            {
                try
                {
                    info = new PlayTogeInfo();
                    JSONObject subJson = array.getJSONObject(i);
                    info.setId(subJson.getString("id"));
                    info.setSalecount(subJson.getInt("salecount"));
                    info.setStock(subJson.getInt("stock"));
                    info.setName(subJson.getString("name"));
                    info.setS_time(subJson.getLong("s_time"));
                    info.setE_time(subJson.getLong("e_time"));
                    info.setImg(subJson.getString("img"));
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
