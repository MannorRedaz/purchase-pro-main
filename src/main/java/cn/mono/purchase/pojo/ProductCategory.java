package cn.mono.purchase.pojo;

public class ProductCategory {
    private Integer pid;

    private String product_name;

    private String types;

    private Integer sid;

    private String tag;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name == null ? null : product_name.trim();
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public ProductCategory() {
    }

    public ProductCategory(Integer pid, String product_name, String types, Integer sid, String tag) {
        this.pid = pid;
        this.product_name = product_name;
        this.types = types;
        this.sid = sid;
        this.tag = tag;
    }
}