package com.travel.dx.godaxing.modules.near.util;

import com.travel.dx.godaxing.modules.near.bean.TogetherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TogetherParse {

    /**
     * 解析游戏列表的数据
     *
     * @param json
     * @return
     */
    public static List<TogetherInfo> parseTogetherList(String json) {
        List<TogetherInfo> list = new ArrayList<>();
        TogetherInfo info = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                try {
                    info = new TogetherInfo();
                    JSONObject subJson = array.getJSONObject(i);
                    info.setId(subJson.getString("id"));
                    info.setName(subJson.getString("name"));
                    info.setDistance(subJson.getString("distance"));
                    info.setIv(subJson.getString("img"));
                    info.setPrice(subJson.getString("price"));
                    info.setNum_people(subJson.getString("salecount"));
                    info.setStock(subJson.getString("stock"));
                    info.setStartTime(subJson.getString("s_time"));
                    info.setEndTime(subJson.getString("e_time"));
                    list.add(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
