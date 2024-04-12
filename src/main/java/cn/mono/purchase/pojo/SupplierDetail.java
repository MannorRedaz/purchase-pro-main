package cn.mono.purchase.pojo;

import java.util.Date;

public class SupplierDetail {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SupplierDetail(int id, String category, String boardName, String productName, int b, Date date, int reallyPrice) {
        this.id = id;
        this.category = category;
        this.boardName = boardName;
        this.productName = productName;
        this.b = b;
        this.date = date;
        this.reallyPrice = reallyPrice;
    }

    private String category;
    private String boardName;
    private String productName;
    private int b ;
    private Date date;
    private int reallyPrice;

    public SupplierDetail() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReallyPrice() {
        return reallyPrice;
    }

    public void setReallyPrice(int reallyPrice) {
        this.reallyPrice = reallyPrice;
    }

    public SupplierDetail(String category, String boardName, String productName, int b, Date date, int reallyPrice) {
        this.category = category;
        this.boardName = boardName;
        this.productName = productName;
        this.b = b;
        this.date = date;
        this.reallyPrice = reallyPrice;
    }
}
