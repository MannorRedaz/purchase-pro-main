package cn.mono.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 秋雨
 * @since : 2024/04/12
 * Description  :
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRes<T> {
    private String token;
    private T data;

    public LoginRes(String token) {
        this.token = token;
    }
}
