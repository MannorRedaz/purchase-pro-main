package cn.mono.purchase.pojo;

import java.util.Date;

public class Supplier {
    private Integer id;

    private Integer sid;

    private Integer cid;

    private String contract_name;

    private String tel;

    private String name;

    private String pwd;

    private String account;

    private String company_name;

    private String company_place;

    private Integer access;

    private String corporation;

    private String corporation_tel;

    private String brief_introduction;

    private String tag;

    private Date last_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name == null ? null : contract_name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name == null ? null : company_name.trim();
    }

    public String getCompany_place() {
        return company_place;
    }

    public void setCompany_place(String company_place) {
        this.company_place = company_place == null ? null : company_place.trim();
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation == null ? null : corporation.trim();
    }

    public String getCorporation_tel() {
        return corporation_tel;
    }

    public void setCorporation_tel(String corporation_tel) {
        this.corporation_tel = corporation_tel == null ? null : corporation_tel.trim();
    }

    public String getBrief_introduction() {
        return brief_introduction;
    }

    public void setBrief_introduction(String brief_introduction) {
        this.brief_introduction = brief_introduction == null ? null : brief_introduction.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Date getLast_time() {
        return last_time;
    }

    public void setLast_time(Date last_time) {
        this.last_time = last_time;
    }
}