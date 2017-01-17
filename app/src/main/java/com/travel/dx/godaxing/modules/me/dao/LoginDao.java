package com.travel.dx.godaxing.modules.me.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.me.bean.LoginInfo;
import com.travel.dx.godaxing.modules.me.util.LoginParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class LoginDao {
    /**
     * 请求推荐列表的数据
     * page=1&lng=4.9E-324&lat=4.9E-324
     */
    public static void requestCommendLogin(String username, String password, final BaseCallBack callBack) {
        HashMap<String, String> params = new HashMap<>();
        params.put("password", password);
        params.put("mobile", username);


        HttpUtil.doHttpReqeust("POST",
                "http://dxlv.iyoudaxing.com/Api/User/signInMobile", params,
                new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        // TODO 请求成功后的处理
                        if (callBack != null) {
                            LoginInfo tempInfo = LoginParse.parseLoginmessage(data.toString());
                            callBack.success(tempInfo);
                        }
                    }

                    @Override
                    public void failed(int errorCode, Object data) {
                        // TODO 请求失败后的处理
                        if (callBack != null) {
                            callBack.failed(errorCode, data);
                        }
                    }
                });
    }
}
