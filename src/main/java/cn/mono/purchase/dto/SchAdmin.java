package cn.mono.purchase.dto;

/**
 * @author nihao
 * @time 2021/4/26
 */
public class SchAdmin {
        private String contract_name;

        private String tel;

        private String count;

        private String name;
        private String schoolName;
        private String tag;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public SchAdmin(String contract_name, String tel, String count, String name, String schoolName, String tag) {
        this.contract_name = contract_name;
        this.tel = tel;
        this.count = count;
        this.name = name;
        this.schoolName = schoolName;
        this.tag = tag;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public SchAdmin() {
    }

    public SchAdmin(String contract_name, String tel, String count, String name, String schoolName) {
        this.contract_name = contract_name;
        this.tel = tel;
        this.count = count;
        this.name = name;
        this.schoolName = schoolName;
    }
}
