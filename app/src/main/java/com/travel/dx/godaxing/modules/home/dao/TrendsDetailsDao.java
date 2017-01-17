package com.travel.dx.godaxing.modules.home.dao;

import android.util.Log;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.TrendsDetailsInfo;
import com.travel.dx.godaxing.modules.home.util.TrendsDetailsParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class TrendsDetailsDao {
    public static void requestCommendItemList(String trendsId,final BaseCallBack baseCallBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", trendsId);
        params.put("lng","4.9E-324");
        params.put("lat","4.9E-324");
        HttpUtil.doHttpReqeust("POST",
                "http://dxlv.iyoudaxing.com/Api/Line/getContent/id/?", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (baseCallBack != null)
                        {
                           TrendsDetailsInfo.DataBean tempList=  TrendsDetailsParse.TrendsDetails(data.toString());
                            baseCallBack.success(tempList);
                            Log.e("**", "tmpList"+tempList );
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
