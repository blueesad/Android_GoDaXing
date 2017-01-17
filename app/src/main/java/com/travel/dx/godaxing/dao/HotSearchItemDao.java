package com.travel.dx.godaxing.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.near.bean.CommendInfo;
import com.travel.dx.godaxing.util.SearchParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class HotSearchItemDao {
    public static void requestSearchItemList(int page,String key, final BaseCallBack callBack)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("lng", "4.9E-324");
        params.put("type", "sight");
        params.put("page", page+"");
//        params.put("par_key", "af0e511e2ab3817bf31abe50e2a960a8");
        params.put("key",key);
        params.put("lat", "4.9E-324");

        HttpUtil.doHttpReqeust("POST",
                "http://dxlv.iyoudaxing.com/Api/Search/searchByKey", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            List<CommendInfo> tempList= SearchParse.parseSearchList(data.toString());
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
