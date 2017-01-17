package com.travel.dx.godaxing.modules.me.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.me.bean.LoginInfo;
import com.travel.dx.godaxing.modules.me.util.ExitParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class ExitDao {
    /**
     * 请求推荐列表的数据
     * page=1&lng=4.9E-324&lat=4.9E-324
     */
    public static void requestCommendExit(String token, final BaseCallBack callBack)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        HttpUtil.doHttpReqeust("POST",
                "http://dxlv.iyoudaxing.com/Api/User/signOut?", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            LoginInfo exitinfo= ExitParse.parseExitmessage(data.toString());
                         callBack.success(exitinfo);
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
