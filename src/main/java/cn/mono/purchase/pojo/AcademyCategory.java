package cn.mono.purchase.pojo;

public class AcademyCategory {
    private Integer cid;

    private Integer sid;

    private String academy_name;

    private Integer budget;

    private Integer count;

    private Integer maximum_order;

    private String tag;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getAcademy_name() {
        return academy_name;
    }

    public void setAcademy_name(String academy_name) {
        this.academy_name = academy_name == null ? null : academy_name.trim();
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMaximum_order() {
        return maximum_order;
    }

    public void setMaximum_order(Integer maximum_order) {
        this.maximum_order = maximum_order;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public AcademyCategory(Integer cid, Integer sid, String academy_name, Integer budget, Integer count, Integer maximum_order, String tag) {
        this.cid = cid;
        this.sid = sid;
        this.academy_name = academy_name;
        this.budget = budget;
        this.count = count;
        this.maximum_order = maximum_order;
        this.tag = tag;
    }

    public AcademyCategory() {
    }
}