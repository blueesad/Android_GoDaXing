package com.travel.dx.godaxing.dao;

import android.util.Log;

import com.travel.dx.godaxing.bean.CommonDetailsInfo;
import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.i.DetailsType;
import com.travel.dx.godaxing.util.CommonDetails_DataParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class CommonDetails_DataDao {
    private static String url;

    public static void requestShopDetailsDataList(final int type, String id, final BaseCallBack callBack)
    {
        if (type == DetailsType.TYPE_SIGHT){
            url ="http://dxlv.iyoudaxing.com/Api/Sight/getContent/id/?";
        }else if (type == DetailsType.TYPE_PLAY){
            url ="http://dxlv.iyoudaxing.com/Api/Play/getContent/id/?";
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id );
        HttpUtil.doHttpReqeust("GET",
                url, params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            Log.i("--------------",data.toString());
                            CommonDetailsInfo.DataBean tempList= CommonDetails_DataParse.parseShopDetails(type,data.toString());
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
