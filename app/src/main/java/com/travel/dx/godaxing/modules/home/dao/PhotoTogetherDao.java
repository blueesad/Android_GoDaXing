package com.travel.dx.godaxing.modules.home.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.PhotoInfo;
import com.travel.dx.godaxing.modules.home.util.PhotoCommendTogetherParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class PhotoTogetherDao {
    public static void requestPhotoList(int page, final BaseCallBack callBack)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", page + "");
        HttpUtil.doHttpReqeust("GET",
                "http://dxlv.iyoudaxing.com/Api/Share/index/type/play/page/", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            List<PhotoInfo.DataBean> tempList = PhotoCommendTogetherParse
                                    .parsePhotoList(data.toString());
                            callBack.success(tempList);
                        }
                    }

                    @Override
                    public void failed(int errorCode, Object data)
                    {
                        // TODO 请求失败后的处理
                        if (callBack != null)
                        {
                            callBack.failed(errorCode, data);
                        }
                    }
                });
    }

}
