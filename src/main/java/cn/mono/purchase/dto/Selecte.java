package cn.mono.purchase.dto;

import java.util.Date;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/16
 */
public class Selecte {

    List<Date> value1;
    String academy;
    String categories;
    String type;


    @Override
    public String toString() {
        return "Selecte{" +
                "value1=" + value1 +
                ", academy='" + academy + '\'' +
                ", categories='" + categories + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Selecte() {
    }

    public List<Date> getValue1() {
        return value1;
    }

    public void setValue1(List<Date> value1) {
        this.value1 = value1;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Selecte(List<Date> value1, String academy, String categories, String type) {
        this.value1 = value1;
        this.academy = academy;
        this.categories = categories;
        this.type = type;
    }
}
