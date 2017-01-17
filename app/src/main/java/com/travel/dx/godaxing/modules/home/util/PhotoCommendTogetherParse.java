package com.travel.dx.godaxing.modules.home.util;

import com.travel.dx.godaxing.modules.home.bean.PhotoInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class PhotoCommendTogetherParse {


    public static List<PhotoInfo.DataBean> parsePhotoList(String json) {
        List<PhotoInfo.DataBean.ImagesBean> imagesList;

        List<PhotoInfo.DataBean> dataList = new ArrayList<>();
        PhotoInfo.DataBean.ImagesBean imagesInfo = null;
        PhotoInfo.DataBean.UserBean usersInfo = null;
        PhotoInfo.DataBean dataInfo = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                dataInfo = new PhotoInfo.DataBean();
                imagesList = new ArrayList<>();
                JSONObject subJson = dataArray.getJSONObject(i);
                JSONArray array = dataArray.getJSONObject(i).getJSONArray("images");
                for (int j = 0; j < array.length(); j++) {
                    imagesInfo = new PhotoInfo.DataBean.ImagesBean();
                    JSONObject subJson2 = array.getJSONObject(j);
                    imagesInfo.setImage_path(subJson2.getString("image_path"));
                    imagesList.add(imagesInfo);
                }
                JSONObject array2 = dataArray.getJSONObject(i).getJSONObject("user");
                usersInfo = new PhotoInfo.DataBean.UserBean();
                usersInfo.setImg(array2.getString("img"));
                usersInfo.setNickname(array2.getString("nickname"));
                usersInfo.setCreatetime(array2.getString("createtime"));


                dataInfo.setUser(usersInfo);
                dataInfo.setImages(imagesList);
                dataInfo.setCreate_time(subJson.getString("create_time"));
                dataInfo.setTitle(subJson.getString("title"));
                dataInfo.setContent(subJson.getString("content"));
                dataList.add(dataInfo);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
