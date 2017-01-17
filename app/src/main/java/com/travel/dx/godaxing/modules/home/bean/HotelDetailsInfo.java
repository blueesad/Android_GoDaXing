package com.travel.dx.godaxing.modules.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class HotelDetailsInfo {

    /**
     * id : 7
     * name : 北京尚都凯瑞国际酒店
     * content :         北京尚都凯瑞国际酒店酒店座落于大兴区中心黄村镇，交通便捷，旺中取静，地理位置优越，由上都凯瑞投资有限管理公司斥资打造的国际性的典雅休闲酒店。
     酒店集客房、餐饮、洗浴、会议为一体，秉承着休闲、养生、时尚、健康的经营理念，为尊贵宾客构筑起全新的休憩空间。酒店拥有各类客房，设施齐全。同时，酒店配备尚都食府中餐厅和尚式铁板烧等特色餐厅，各色风味美食齐聚。大小不等的会议室，是便捷商务会议之选。
     * traffic : 京开高速公路大兴城区/黄村出口离开进入兴丰南大街
     * price : 0
     * img_id : 12
     * address : 北京市大兴区黄村镇兴丰大街(三段)60号(兴丰南大街与富强路交叉口往南)
     * tagging :
     * lat : 39.629941
     * lng : 116.309801
     * phone : 69259999
     * cerate_time : 1432103455
     * publish_time : 1432051200
     * last_modify_time : 1439791245
     * label : 0
     * author : 7
     * sight_spot :
     * collection : 0
     * share : 0
     * distance : 1.224068211919E7
     * images : [{"id":"12","obj_id":"7","img_path":"http://dxlv.iyoudaxing.com/images/hotel/555c29c143e00/555c29c143e00.jpg","is_main":"1"}]
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String name;
        private String content;
        private String traffic;
        private String price;
        private String img_id;
        private String address;
        private String tagging;
        private String lat;
        private String lng;
        private String phone;
        private String cerate_time;
        private String publish_time;
        private String last_modify_time;
        private String label;
        private String author;
        private String sight_spot;
        private String collection;
        private String share;
        private double distance;
        /**
         * id : 12
         * obj_id : 7
         * img_path : http://dxlv.iyoudaxing.com/images/hotel/555c29c143e00/555c29c143e00.jpg
         * is_main : 1
         */

        private List<ImagesBean> images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTraffic() {
            return traffic;
        }

        public void setTraffic(String traffic) {
            this.traffic = traffic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTagging() {
            return tagging;
        }

        public void setTagging(String tagging) {
            this.tagging = tagging;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCerate_time() {
            return cerate_time;
        }

        public void setCerate_time(String cerate_time) {
            this.cerate_time = cerate_time;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getLast_modify_time() {
            return last_modify_time;
        }

        public void setLast_modify_time(String last_modify_time) {
            this.last_modify_time = last_modify_time;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSight_spot() {
            return sight_spot;
        }

        public void setSight_spot(String sight_spot) {
            this.sight_spot = sight_spot;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            private String id;
            private String obj_id;
            private String img_path;
            private String is_main;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getObj_id() {
                return obj_id;
            }

            public void setObj_id(String obj_id) {
                this.obj_id = obj_id;
            }

            public String getImg_path() {
                return img_path;
            }

            public void setImg_path(String img_path) {
                this.img_path = img_path;
            }

            public String getIs_main() {
                return is_main;
            }

            public void setIs_main(String is_main) {
                this.is_main = is_main;
            }
        }
    }
}

