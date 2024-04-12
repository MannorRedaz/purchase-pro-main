package cn.mono.purchase.pojo;

import java.util.Date;

public class MSG {
    private String msg;
    private Date date;
    private String tel;
    private  String name;

    public MSG() {
    }

    @Override
    public String toString() {
        return "你好：" +
                "msg='" + msg + '\'' +
                ", date=" + date +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }

    public String getTel() {
        return tel;
    }

    public String getName() {
        return name;
    }

    public MSG(String msg, Date date, String tel, String name) {
        this.msg = msg;
        this.date = date;
        this.tel = tel;
        this.name = name;
    }
}