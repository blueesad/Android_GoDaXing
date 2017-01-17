package com.travel.dx.godaxing.modules.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class PhotoInfo {

    /**
     * id : 118
     * uid : 42
     * content : 味道真不错
     * type : sight
     * boj_id : 159
     * create_time : 1473648212
     * status : 1
     * default_img : 299
     * user : {"id":"42","mobile":"13691589492","openid":null,"password":"fcea920f7412b5da7be0cf42b8c93759","nickname":null,"email":null,"img":"http://dxlv.iyoudaxing.com","score":"99","sex":"0","status":"1","ind_signature":null,"createtime":"1437112942","lastlogin":"1479777989"}
     * images : [{"id":"299","uid":"42","image_path":"http://dxlv.iyoudaxing.com/userImg/57d61654b4aa942/album/57d61654b1097.jpg","image_type":"2","obj_id":"118"}]
     * title : 现摘的采育有机葡萄现半价仅42元/箱
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String id;
        private String uid;
        private String content;
        private String type;
        private String boj_id;
        private String create_time;
        private String status;
        private String default_img;
        /**
         * id : 42
         * mobile : 13691589492
         * openid : null
         * password : fcea920f7412b5da7be0cf42b8c93759
         * nickname : null
         * email : null
         * img : http://dxlv.iyoudaxing.com
         * score : 99
         * sex : 0
         * status : 1
         * ind_signature : null
         * createtime : 1437112942
         * lastlogin : 1479777989
         */

        private UserBean user;
        private String title;
        /**
         * id : 299
         * uid : 42
         * image_path : http://dxlv.iyoudaxing.com/userImg/57d61654b4aa942/album/57d61654b1097.jpg
         * image_type : 2
         * obj_id : 118
         */

        private List<ImagesBean> images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBoj_id() {
            return boj_id;
        }

        public void setBoj_id(String boj_id) {
            this.boj_id = boj_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDefault_img() {
            return default_img;
        }

        public void setDefault_img(String default_img) {
            this.default_img = default_img;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class UserBean implements Serializable{
            private String id;
            private String mobile;
            private Object openid;
            private String password;
            private String nickname;
            private Object email;
            private String img;
            private String score;
            private String sex;
            private String status;
            private Object ind_signature;
            private String createtime;
            private String lastlogin;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getInd_signature() {
                return ind_signature;
            }

            public void setInd_signature(Object ind_signature) {
                this.ind_signature = ind_signature;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getLastlogin() {
                return lastlogin;
            }

            public void setLastlogin(String lastlogin) {
                this.lastlogin = lastlogin;
            }
        }

        public static class ImagesBean implements Serializable{
            private String id;
            private String uid;
            private String image_path;
            private String title_image;
            private String image_type;
            private String obj_id;

            public String getTitle_image() {
                return title_image;
            }

            public void setTitle_image(String title_image) {
                this.title_image = title_image;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getImage_path() {
                return image_path;
            }

            public void setImage_path(String image_path) {
                this.image_path = image_path;
            }

            public String getImage_type() {
                return image_type;
            }

            public void setImage_type(String image_type) {
                this.image_type = image_type;
            }

            public String getObj_id() {
                return obj_id;
            }

            public void setObj_id(String obj_id) {
                this.obj_id = obj_id;
            }
        }
    }
}
