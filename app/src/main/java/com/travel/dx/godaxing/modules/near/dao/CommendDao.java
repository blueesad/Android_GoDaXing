package com.travel.dx.godaxing.modules.near.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.near.bean.CommendInfo;
import com.travel.dx.godaxing.modules.near.util.CommendParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class CommendDao {
    /**
     * 请求推荐列表的数据
     * page=1&lng=4.9E-324&lat=4.9E-324
     */
    public static void requestCommendList(int page, final BaseCallBack callBack)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", page+"");
        params.put("lng", "4.9E-324");
        params.put("lat", "4.9E-324");

        HttpUtil.doHttpReqeust("POST",
                "http://dxlv.iyoudaxing.com/Api/Sight/near/page/?", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            List<CommendInfo> tempList= CommendParse.parseCommendList(data.toString());
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
