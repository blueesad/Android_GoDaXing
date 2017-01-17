package com.travel.dx.godaxing.modules.shop.util;

import com.travel.dx.godaxing.modules.shop.bean.ShopInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class ShopParse {
    public static List<ShopInfo> parseShopList(String json)
    {
        List<ShopInfo> list = new ArrayList<>();
        ShopInfo info = null;
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("data");
            for (int i = 0; i < array.length(); i++)
            {
                try
                {
                    info = new ShopInfo();
                    JSONObject subJson = array.getJSONObject(i);
                    info.setId(subJson.getInt("id"));
                    info.setName(subJson.getString("name"));
                    info.setPrice(subJson.getInt("price"));
                    info.setOldprice(subJson.getInt("originalprice"));
                    info.setImg(subJson.getString("img"));
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
