package cn.mono.purchase.dto;

import cn.mono.purchase.exception.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Message {
    private boolean success = false;
    private String msg;
    private List date;
    /**
     * 状态码 成功 1 失败 0
     */
    private Integer status = 0;

    //代表是何种用户
    private int p;

    public Message(boolean success, String msg, List date, int p) {
        this.success = success;
        this.msg = msg;
        this.date = date;
        this.p = p;
    }

    public Message(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public void clear() {
        success = false;
        msg = "";
        date = null;
        p = 0;
    }

    public static Message error(ErrorCodeEnum errorCodeEnum) {
        Message result = new Message();
        result.status = 0;
        result.msg = errorCodeEnum.getMsg();
        return result;
    }

    public static Message error(String data) {
        Message result = new Message();
        result.status = 0;
        result.msg = data;
        return result;
    }


}
