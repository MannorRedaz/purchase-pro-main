package cn.mono.purchase.pojo;

import java.util.Date;

public class ApplyForShortlist {
    private Integer id;

    private Integer sid;

    private Integer scid;

    private Date date;

    private String recommends;

    private String academy_access;

    private Integer school_administrator_access;

    private String causes;

    private String tag;

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

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRecommends() {
        return recommends;
    }

    public void setRecommends(String recommends) {
        this.recommends = recommends == null ? null : recommends.trim();
    }

    public String getAcademy_access() {
        return academy_access;
    }

    public void setAcademy_access(String academy_access) {
        this.academy_access = academy_access == null ? null : academy_access.trim();
    }

    public Integer getSchool_administrator_access() {
        return school_administrator_access;
    }

    public void setSchool_administrator_access(Integer school_administrator_access) {
        this.school_administrator_access = school_administrator_access;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes == null ? null : causes.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}