package com.travel.dx.godaxing.modules.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class HomeDetailsInfo {

    /**
     * id : 164
     * name : 北京爱情海玫瑰文化博览园（月季文化园）
     * isort : 0
     * gid : 638
     * goodsdesc : 这片“爱情圣地”，有足够的浪漫 甜蜜
     * lat : 39.681713
     * lng : 116.46483
     * collection : 0
     * showscore : 0
     * yuejika :
     * share : 0
     * classify : 0
     * shelves : 1
     * img : http://dxlv.iyoudaxing.com/images/sight/582036b654b4b/582036b654b4b.jpg
     * goodsname : 北京爱情海玫瑰文化博览园（月季文化园）
     * price : 60
     * norefund : 0
     * stock : 100
     * type : 1
     * score : 0
     * originalprice : 60
     * score_price : 0
     * sold : 0
     * distance : 1.2251609352542E7
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String name;
        private String isort;
        private String gid;
        private String goodsdesc;
        private String lat;
        private String lng;
        private String collection;
        private String showscore;
        private String yuejika;
        private String share;
        private String classify;
        private String shelves;
        private String img;
        private String goodsname;
        private String price;
        private String norefund;
        private String stock;
        private String type;
        private String score;
        private String originalprice;
        private String score_price;
        private String sold;
        private double distance;

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

        public String getIsort() {
            return isort;
        }

        public void setIsort(String isort) {
            this.isort = isort;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getGoodsdesc() {
            return goodsdesc;
        }

        public void setGoodsdesc(String goodsdesc) {
            this.goodsdesc = goodsdesc;
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

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getShowscore() {
            return showscore;
        }

        public void setShowscore(String showscore) {
            this.showscore = showscore;
        }

        public String getYuejika() {
            return yuejika;
        }

        public void setYuejika(String yuejika) {
            this.yuejika = yuejika;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }

        public String getShelves() {
            return shelves;
        }

        public void setShelves(String shelves) {
            this.shelves = shelves;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNorefund() {
            return norefund;
        }

        public void setNorefund(String norefund) {
            this.norefund = norefund;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getOriginalprice() {
            return originalprice;
        }

        public void setOriginalprice(String originalprice) {
            this.originalprice = originalprice;
        }

        public String getScore_price() {
            return score_price;
        }

        public void setScore_price(String score_price) {
            this.score_price = score_price;
        }

        public String getSold() {
            return sold;
        }

        public void setSold(String sold) {
            this.sold = sold;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
