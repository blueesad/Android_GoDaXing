package com.travel.dx.godaxing.dao;

import com.travel.dx.godaxing.i.BaseCallBack;
import com.travel.dx.godaxing.bean.SearchInfo;
import com.travel.dx.godaxing.util.HotSearchParse;
import com.travel.dx.godaxing.net.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class HotSearchDao {
    /**
     * 请求搜索列表的数据
     *
     */
    public static void requestHotSearchList( final BaseCallBack callBack)
    {
        HashMap<String, String> params = new HashMap<>();
        HttpUtil.doHttpReqeust("GET",
                "http://dxlv.iyoudaxing.com/Api/Search", null,
                new BaseCallBack()
                {
                    @Override
                    public void success(Object data)
                    {
                        // TODO 请求成功后的处理
                        if (callBack != null)
                        {
                            List<SearchInfo> tempList= HotSearchParse.parseHotSearchList(data.toString());
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
