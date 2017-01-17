package com.travel.dx.godaxing.modules.near.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.near.bean.CommendItemInfo;
import com.travel.dx.godaxing.modules.near.util.CommendItemParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class CommendItemDao {
    /**
     * 请求详细列表的数据
     * id
     */
    public static void requestCommendItemList(String commendId, final BaseCallBack callBack)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", commendId);
        HttpUtil.doHttpReqeust("GET",
                "http://dxlv.iyoudaxing.com/Api/Sight/getContent/id/?", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            CommendItemInfo.DataBean tempList= CommendItemParse.parseCommendItem(data.toString());
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
