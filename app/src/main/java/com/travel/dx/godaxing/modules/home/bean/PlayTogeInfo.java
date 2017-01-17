package com.travel.dx.godaxing.modules.home.bean;

/**
 * Created by Administrator on 2016/11/22 0022.
 */
public class PlayTogeInfo {

    private String id;
    private String name;
    private long s_time;
    private long e_time;
    private long a_time;
    private String price;
    private String originalprice;
    private  String img;
    private  int salecount;//已售的
    private  int stock;//总的票数





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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getE_time() {
        return e_time;
    }

    public void setE_time(long e_time) {
        this.e_time = e_time;
    }

    public long getA_time() {
        return a_time;
    }

    public void setA_time(long a_time) {
        this.a_time = a_time;
    }

    public long getS_time() {
        return s_time;
    }

    public void setS_time(long s_time) {
        this.s_time = s_time;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSalecount() {
        return salecount;
    }

    public void setSalecount(int salecount) {
        this.salecount = salecount;
    }
}
