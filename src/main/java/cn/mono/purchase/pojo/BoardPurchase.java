package cn.mono.purchase.pojo;

import java.util.Date;

public class BoardPurchase {
    private Integer id;

    private Integer pid;

    private String purchase_name;

    private Date publish_time;

    private Date start_time;

    private Date end_time;

    private String title;

    private String purpose;

    private String site;

    private Date site_inspection_time;

    private String qualifications;

    private String notifiedcation;

    private String errol_contract;

    private String errol_tel;

    private Integer errol_type;

    private String inspection_contract;

    private String inspection_tel;

    private Integer click_count;

    private String file_place;

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

    public String getPurchase_name() {
        return purchase_name;
    }

    public void setPurchase_name(String purchase_name) {
        this.purchase_name = purchase_name == null ? null : purchase_name.trim();
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public Date getSite_inspection_time() {
        return site_inspection_time;
    }

    public void setSite_inspection_time(Date site_inspection_time) {
        this.site_inspection_time = site_inspection_time;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications == null ? null : qualifications.trim();
    }

    public String getNotifiedcation() {
        return notifiedcation;
    }

    public void setNotifiedcation(String notifiedcation) {
        this.notifiedcation = notifiedcation == null ? null : notifiedcation.trim();
    }

    public String getErrol_contract() {
        return errol_contract;
    }

    public void setErrol_contract(String errol_contract) {
        this.errol_contract = errol_contract == null ? null : errol_contract.trim();
    }

    public String getErrol_tel() {
        return errol_tel;
    }

    public void setErrol_tel(String errol_tel) {
        this.errol_tel = errol_tel == null ? null : errol_tel.trim();
    }

    public Integer getErrol_type() {
        return errol_type;
    }

    public void setErrol_type(Integer errol_type) {
        this.errol_type = errol_type;
    }

    public String getInspection_contract() {
        return inspection_contract;
    }

    public void setInspection_contract(String inspection_contract) {
        this.inspection_contract = inspection_contract == null ? null : inspection_contract.trim();
    }

    public String getInspection_tel() {
        return inspection_tel;
    }

    public void setInspection_tel(String inspection_tel) {
        this.inspection_tel = inspection_tel == null ? null : inspection_tel.trim();
    }

    public Integer getClick_count() {
        return click_count;
    }

    public void setClick_count(Integer click_count) {
        this.click_count = click_count;
    }

    public String getFile_place() {
        return file_place;
    }

    public void setFile_place(String file_place) {
        this.file_place = file_place == null ? null : file_place.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public BoardPurchase() {
    }

    public BoardPurchase(Integer id, Integer pid, String purchase_name, Date publish_time, Date start_time, Date end_time, String title, String purpose, String site, Date site_inspection_time, String qualifications, String notifiedcation, String errol_contract, String errol_tel, Integer errol_type, String inspection_contract, String inspection_tel, Integer click_count, String file_place, String tag) {
        this.id = id;
        this.pid = pid;
        this.purchase_name = purchase_name;
        this.publish_time = publish_time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
        this.purpose = purpose;
        this.site = site;
        this.site_inspection_time = site_inspection_time;
        this.qualifications = qualifications;
        this.notifiedcation = notifiedcation;
        this.errol_contract = errol_contract;
        this.errol_tel = errol_tel;
        this.errol_type = errol_type;
        this.inspection_contract = inspection_contract;
        this.inspection_tel = inspection_tel;
        this.click_count = click_count;
        this.file_place = file_place;
        this.tag = tag;
    }
}