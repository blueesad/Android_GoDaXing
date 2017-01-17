package com.travel.dx.godaxing.modules.me.bean;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class MeansInfo {

    /**
     * status : 1
     * data : {"mobile":"18039002582","nickname":"sfasdfsf","email":null,"img":"http://dxlv.iyoudaxing.com","score":"0","sex":"1","ind_signature":"qwwf"}
     * msg : 获取用户信息成功
     * token : 1674263efb3a3cd3f1d314ee8fa27a92
     * chooses : null
     */

    private int status;
    /**
     * mobile : 18039002582
     * nickname : sfasdfsf
     * email : null
     * img : http://dxlv.iyoudaxing.com
     * score : 0
     * sex : 1
     * ind_signature : qwwf
     */

    private String msg;
    private String token;
    private Object chooses;
    private String mobile;
    private String nickname;
    private String email;
    private String img;
    private String score;
    private String sex;
    private String ind_signature;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getChooses() {
        return chooses;
    }

    public void setChooses(Object chooses) {
        this.chooses = chooses;
    }




        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getInd_signature() {
            return ind_signature;
        }

        public void setInd_signature(String ind_signature) {
            this.ind_signature = ind_signature;
        }

}
