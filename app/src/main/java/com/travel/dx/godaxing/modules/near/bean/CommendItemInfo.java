package com.travel.dx.godaxing.modules.near.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class CommendItemInfo {
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
        private String pointout;
        private String img_id;
        private String pdu_id;
        private String address;
        private String lat;
        private String lng;
        private String phone;
        private String notice;
        private String price;
        private String originalprice;
        private String yuejika;
        private String showscore;
        private String cerate_time;
        private String publish_time;
        private String last_modify_time;
        private String label;
        private String author;
        private String collection;
        private String share;
        private String promotion;
        private String rank;
        private String classify;
        private String shelves;
        private String scontent;
        private String goodsdesc;
        private String isort;
        private double distance;
        private List<GoodsBean> goods;
        /**
         * img_path : http://dxlv.iyoudaxing.com
         */

        private List<SImageBean> s_image;
        /**
         * img_path : http://dxlv.iyoudaxing.com/images/sight/561dfc84579d1/561dfc84579d1.jpg
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

        public String getPointout() {
            return pointout;
        }

        public void setPointout(String pointout) {
            this.pointout = pointout;
        }

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getPdu_id() {
            return pdu_id;
        }

        public void setPdu_id(String pdu_id) {
            this.pdu_id = pdu_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOriginalprice() {
            return originalprice;
        }

        public void setOriginalprice(String originalprice) {
            this.originalprice = originalprice;
        }

        public String getYuejika() {
            return yuejika;
        }

        public void setYuejika(String yuejika) {
            this.yuejika = yuejika;
        }

        public String getShowscore() {
            return showscore;
        }

        public void setShowscore(String showscore) {
            this.showscore = showscore;
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

        public String getPromotion() {
            return promotion;
        }

        public void setPromotion(String promotion) {
            this.promotion = promotion;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
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

        public String getScontent() {
            return scontent;
        }

        public void setScontent(String scontent) {
            this.scontent = scontent;
        }

        public String getGoodsdesc() {
            return goodsdesc;
        }

        public void setGoodsdesc(String goodsdesc) {
            this.goodsdesc = goodsdesc;
        }

        public String getIsort() {
            return isort;
        }

        public void setIsort(String isort) {
            this.isort = isort;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<SImageBean> getS_image() {
            return s_image;
        }

        public void setS_image(List<SImageBean> s_image) {
            this.s_image = s_image;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class GoodsBean {
            private String id;
            private String name;
            private String price;
            private String originalprice;
            private String score_price;
            private String content;
            private String type;
            private String stock;
            private String s_time;
            private String e_time;
            private String score;
            private String sold;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getS_time() {
                return s_time;
            }

            public void setS_time(String s_time) {
                this.s_time = s_time;
            }

            public String getE_time() {
                return e_time;
            }

            public void setE_time(String e_time) {
                this.e_time = e_time;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getSold() {
                return sold;
            }

            public void setSold(String sold) {
                this.sold = sold;
            }
        }

        public static class SImageBean {
            private String img_path;

            public String getImg_path() {
                return img_path;
            }

            public void setImg_path(String img_path) {
                this.img_path = img_path;
            }
        }

        public static class ImagesBean {
            private String img_path;

            public String getImg_path() {
                return img_path;
            }

            public void setImg_path(String img_path) {
                this.img_path = img_path;
            }
        }
    }
}
