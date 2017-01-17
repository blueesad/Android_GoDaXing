package com.travel.dx.godaxing.modules.home.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.YueJiCardInfo;
import com.travel.dx.godaxing.modules.home.util.YueJiCardParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class YueJiCardDao {
    public static void requestYueJiCardList(final BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("lat", "4.9E-324");
        params.put("lng","4.9E-324");
        HttpUtil.doHttpReqeust("POST",
                "http://dxlv.iyoudaxing.com/Api/Card?", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (baseCallBack != null)
                        {
                            YueJiCardInfo.DataBean tempList=  YueJiCardParse.YueJiCard(data.toString());
                            baseCallBack.success(tempList);
                        }
                    }

                    @Override
                    public void failed(int errorCode, Object data)
                    {
                        // TODO 请求失败后的处理
                        if (baseCallBack != null)
                        {
                            baseCallBack.failed(errorCode, data);
                        }
                    }
                });
    }
}
