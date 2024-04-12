package cn.mono.purchase.pojo;

import java.util.Date;

public class Result {
    private Integer id;

    private Integer pid;

    private Integer sid;

    private Integer scis;

    private Integer purchase_id;

    private String actual_product_name;

    private Integer reality_price;

    private Date date;

    private String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getScis() {
        return scis;
    }

    public void setScis(Integer scis) {
        this.scis = scis;
    }

    public Integer getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(Integer purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getActual_product_name() {
        return actual_product_name;
    }

    public void setActual_product_name(String actual_product_name) {
        this.actual_product_name = actual_product_name == null ? null : actual_product_name.trim();
    }

    public Integer getReality_price() {
        return reality_price;
    }

    public void setReality_price(Integer reality_price) {
        this.reality_price = reality_price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Result(Integer pid, Integer sid, Integer scis, Integer purchase_id, String actual_product_name, Integer reality_price, Date date, String tag) {
        this.pid = pid;
        this.sid = sid;
        this.scis = scis;
        this.purchase_id = purchase_id;
        this.actual_product_name = actual_product_name;
        this.reality_price = reality_price;
        this.date = date;
        this.tag = tag;
    }

    public Result() {
    }

    public Result(Integer id, Integer pid, Integer sid, Integer scis, Integer purchase_id, String actual_product_name, Integer reality_price, Date date, String tag) {
        this.id = id;
        this.pid = pid;
        this.sid = sid;
        this.scis = scis;
        this.purchase_id = purchase_id;
        this.actual_product_name = actual_product_name;
        this.reality_price = reality_price;
        this.date = date;
        this.tag = tag;
    }
}