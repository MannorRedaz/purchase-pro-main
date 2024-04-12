package cn.mono.purchase.pojo;

public class Supper {
    private Integer id;

    private String name;

    private String pwd;

    private String contract_name;

    private String tel;

    private String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Supper() {
    }

    public Supper(String name, String pwd, String contract_name, String tel, String tag) {
        this.name = name;
        this.pwd = pwd;
        this.contract_name = contract_name;
        this.tel = tel;
        this.tag = tag;
    }

    public Supper(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public Supper(Integer id, String name, String pwd, String contract_name, String tel, String tag) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.contract_name = contract_name;
        this.tel = tel;
        this.tag = tag;
    }
}