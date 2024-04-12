package cn.mono.purchase.dto;

/**
 * @author nihao
 * @time 2021/4/26
 */
public class PreAdmin {
        private String contract_name;

        private String tel;

        private String count;

        private String name;
        private String academy_name;
        private  int budget;
        private  int maximum_order;
        private  int sid;

    public PreAdmin() {
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

    public String getAcademy_name() {
        return academy_name;
    }

    public void setAcademy_name(String academy_name) {
        this.academy_name = academy_name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getMaximum_order() {
        return maximum_order;
    }

    public void setMaximum_order(int maximum_order) {
        this.maximum_order = maximum_order;
    }

    public PreAdmin(String contract_name, String tel, String count, String name, String academy_name, int budget, int maximum_order, int sid) {
        this.contract_name = contract_name;
        this.tel = tel;
        this.count = count;
        this.name = name;
        this.academy_name = academy_name;
        this.budget = budget;
        this.maximum_order = maximum_order;
        this.sid = sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }
}
