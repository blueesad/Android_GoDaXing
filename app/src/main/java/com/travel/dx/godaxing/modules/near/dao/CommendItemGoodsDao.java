package com.travel.dx.godaxing.modules.near.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.near.bean.CommendItemInfo;
import com.travel.dx.godaxing.modules.near.util.CommendItemGoodsParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class CommendItemGoodsDao {
    public static void requestCommendItemList(String id, final BaseCallBack callBack)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id );
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
                            List<CommendItemInfo.DataBean.GoodsBean> tempList=  CommendItemGoodsParse.parseCommendItem(data.toString());
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
