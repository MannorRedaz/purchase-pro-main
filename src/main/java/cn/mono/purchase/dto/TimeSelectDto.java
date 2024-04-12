package cn.mono.purchase.dto;

/**
 * @author nihao
 * @time 2021/4/22
 */
public class TimeSelectDto {
    String t1;
    String t2;
    int id;
    int pid;

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public TimeSelectDto() {
    }

    public TimeSelectDto(String t1, String t2, int id, int pid) {
        this.t1 = t1;
        this.t2 = t2;
        this.id = id;
        this.pid = pid;
    }
}
