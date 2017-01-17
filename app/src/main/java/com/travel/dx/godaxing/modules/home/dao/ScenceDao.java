package com.travel.dx.godaxing.modules.home.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.ScenceInfo;
import com.travel.dx.godaxing.modules.home.util.ScenceParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class ScenceDao {
    public static void requestScenceList(int page, final  BaseCallBack callBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", page+"");
        params.put("lng", "4.9E-324");
        params.put("lat", "4.9E-324");

        HttpUtil.doHttpReqeust("POST",
                "  http://dxlv.iyoudaxing.com/Api/Sight/getList/page/?", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            List<ScenceInfo> tempList=  ScenceParse.parseScenceDetails(data.toString());

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
