package com.travel.dx.godaxing.modules.home.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.RoutesInfo;
import com.travel.dx.godaxing.modules.home.util.RoutesInfoParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class RoutesDao {
    public  static  void requstTrendsList( int page,final BaseCallBack callBack){
        HashMap<String, String> params = new HashMap<>();
        params.put("page", page+"" );
        HttpUtil.doHttpReqeust("POST",
                "http://dxlv.iyoudaxing.com/Api/Line/getList/page/?", params,
                new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            List<RoutesInfo> tempList= RoutesInfoParse.parseRoutesDetails(data.toString());
                            callBack.success(tempList);
                        }
                    }

                    @Override
                    public void failed(int errorCode, Object data) {
                        // TODO 请求失败后的处理
                        if (callBack != null)
                        {
                            callBack.failed(errorCode, data);
                        }
                    }
                });
    }
}
