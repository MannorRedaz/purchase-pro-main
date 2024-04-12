package cn.mono.purchase.dto;

/**
 * @author nihao
 * @time 2021/3/20
 */

public class Login {
    String name;
    String pwd;

    public Login(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public Login() {
    }

    @Override
    public String toString() {
        return "Login{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
