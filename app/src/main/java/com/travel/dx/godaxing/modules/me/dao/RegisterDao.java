package com.travel.dx.godaxing.modules.me.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.me.bean.LoginInfo;
import com.travel.dx.godaxing.modules.me.util.RegisterParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class RegisterDao {
    /**
     * 请求游戏列表的数据
     */
    public static void requestRegister(String pwd, String code, String mobile, final BaseCallBack callBack)
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("password", pwd);
        params.put("code", code);
        params.put("mobile", mobile);

        HttpUtil.doHttpReqeust("POST",
                "http://dxlv.iyoudaxing.com/Api/User/singupMobile", params,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            LoginInfo temp = RegisterParse.parseRegister(data.toString());
                            callBack.success(temp);
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
