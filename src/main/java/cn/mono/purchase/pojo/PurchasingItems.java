package cn.mono.purchase.pojo;

import java.util.Date;

public class PurchasingItems {
    private Integer id;

    private Integer cid;

    private Integer pid;

    private Integer sid;

    private Date apply_time;

    private String product_name;

    private Integer highest_price;

    private Integer specified;

    private String reason;

    private String tag;

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

    private String rules;

    private String auction_site;

    private Integer is_result;

    private String others;

    private Integer acceess;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name == null ? null : product_name.trim();
    }

    public Integer getHighest_price() {
        return highest_price;
    }

    public void setHighest_price(Integer highest_price) {
        this.highest_price = highest_price;
    }

    public Integer getSpecified() {
        return specified;
    }

    public void setSpecified(Integer specified) {
        this.specified = specified;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
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

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }

    public String getAuction_site() {
        return auction_site;
    }

    public void setAuction_site(String auction_site) {
        this.auction_site = auction_site == null ? null : auction_site.trim();
    }

    public Integer getIs_result() {
        return is_result;
    }

    public void setIs_result(Integer is_result) {
        this.is_result = is_result;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others == null ? null : others.trim();
    }

    public Integer getAcceess() {
        return acceess;
    }

    public void setAcceess(Integer acceess) {
        this.acceess = acceess;
    }

    @Override
    public String toString() {
        return "Purchasing_items{" +
                "id=" + id +
                ", cid=" + cid +
                ", pid=" + pid +
                ", sid=" + sid +
                ", apply_time=" + apply_time +
                ", product_name='" + product_name + '\'' +
                ", highest_price=" + highest_price +
                ", specified=" + specified +
                ", reason='" + reason + '\'' +
                ", tag='" + tag + '\'' +
                ", purchase_name='" + purchase_name + '\'' +
                ", publish_time=" + publish_time +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", title='" + title + '\'' +
                ", purpose='" + purpose + '\'' +
                ", site='" + site + '\'' +
                ", site_inspection_time=" + site_inspection_time +
                ", qualifications='" + qualifications + '\'' +
                ", notifiedcation='" + notifiedcation + '\'' +
                ", errol_contract='" + errol_contract + '\'' +
                ", errol_tel='" + errol_tel + '\'' +
                ", errol_type=" + errol_type +
                ", inspection_contract='" + inspection_contract + '\'' +
                ", inspection_tel='" + inspection_tel + '\'' +
                ", click_count=" + click_count +
                ", file_place='" + file_place + '\'' +
                ", rules='" + rules + '\'' +
                ", auction_site='" + auction_site + '\'' +
                ", is_result=" + is_result +
                ", others='" + others + '\'' +
                ", acceess=" + acceess +
                '}';
    }

    public PurchasingItems() {
    }

    public PurchasingItems(Integer id, Integer cid, Integer pid, Integer sid, Date apply_time, String product_name, Integer highest_price, Integer specified, String reason, String tag, String purchase_name, Date publish_time, Date start_time, Date end_time, String title, String purpose, String site, Date site_inspection_time, String qualifications, String notifiedcation, String errol_contract, String errol_tel, Integer errol_type, String inspection_contract, String inspection_tel, Integer click_count, String file_place, String rules, String auction_site, Integer is_result, String others, Integer acceess) {
        this.id = id;
        this.cid = cid;
        this.pid = pid;
        this.sid = sid;
        this.apply_time = apply_time;
        this.product_name = product_name;
        this.highest_price = highest_price;
        this.specified = specified;
        this.reason = reason;
        this.tag = tag;
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
        this.rules = rules;
        this.auction_site = auction_site;
        this.is_result = is_result;
        this.others = others;
        this.acceess = acceess;
    }

}