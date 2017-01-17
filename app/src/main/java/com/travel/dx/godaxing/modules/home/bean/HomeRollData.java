package com.travel.dx.godaxing.modules.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class HomeRollData {

    /**
     * status : 1
     * data : {"adv":[{"id":"6","name":"首页1","img":"http://dxlv.iyoudaxing.com/images/adv/581c27688d049/581c27688d049.jpg","tid":"33","type":"1","url":"","top":"0"},{"id":"7","name":"首页2","img":"http://dxlv.iyoudaxing.com/images/adv/5805c787eabe5/5805c787eabe5.jpg","tid":"142","type":"1","url":"","top":"0"},{"id":"20","name":"首页3","img":"http://dxlv.iyoudaxing.com/images/adv/581c40ad90980/581c40ad90980.jpg","tid":"189","type":"4","url":"","top":"0"}]}
     */
    private int status;
    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public static  class DataEntity {
        /**
         * adv : [{"id":"6","name":"首页1","img":"http://dxlv.iyoudaxing.com/images/adv/581c27688d049/581c27688d049.jpg","tid":"33","type":"1","url":"","top":"0"},{"id":"7","name":"首页2","img":"http://dxlv.iyoudaxing.com/images/adv/5805c787eabe5/5805c787eabe5.jpg","tid":"142","type":"1","url":"","top":"0"},{"id":"20","name":"首页3","img":"http://dxlv.iyoudaxing.com/images/adv/581c40ad90980/581c40ad90980.jpg","tid":"189","type":"4","url":"","top":"0"}]
         */
        private List<AdvEntity> adv;

        public void setAdv(List<AdvEntity> adv) {
            this.adv = adv;
        }

        public List<AdvEntity> getAdv() {
            return adv;
        }

        public static class AdvEntity {
            /**
             * id : 6
             * name : 首页1
             * img : http://dxlv.iyoudaxing.com/images/adv/581c27688d049/581c27688d049.jpg
             * tid : 33
             * type : 1
             * url :
             * top : 0
             */
            private String id;
            private String name;
            private String img;
            private String tid;
            private String type;
            private String url;
            private String top;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getImg() {
                return img;
            }

            public String getTid() {
                return tid;
            }

            public String getType() {
                return type;
            }

            public String getUrl() {
                return url;
            }

            public String getTop() {
                return top;
            }
        }
    }
}
