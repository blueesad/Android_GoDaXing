package com.travel.dx.godaxing.modules.home.dao;

import android.util.Log;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.modules.home.bean.PlayTogeInfo;
import com.travel.dx.godaxing.modules.home.util.PlaytogetherParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class PlaytogetherDao {

    public static void requestPlaytogertherinprogressList( int page,final BaseCallBack callBack)
    {
        Log.i("PlaytogetherDao", "requestPlaytogertherinprogressList: "+page);

        HttpUtil.doHttpReqeust("GET",
                "http://dxlv.iyoudaxing.com/Api/Play", null,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            List<PlayTogeInfo> tempList= PlaytogetherParse.parseplayList(data.toString());
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
