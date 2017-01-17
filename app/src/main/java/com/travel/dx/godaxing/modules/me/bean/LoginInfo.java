package com.travel.dx.godaxing.modules.me.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class LoginInfo {


    /**
     * status : 0
     * data : []
     * msg : 验证码无效
     * token : null
     * chooses : null
     */

    private int status;
    private String msg;
    private String token;
    private Object chooses;
    private List<?> data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getChooses() {
        return chooses;
    }

    public void setChooses(Object chooses) {
        this.chooses = chooses;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
