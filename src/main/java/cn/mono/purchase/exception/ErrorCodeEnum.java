package cn.mono.purchase.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : 秋雨
 * @since : 2024/01/28
 * Description  : 系统错误码
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    SUCCESS(200, "成功"),

//    ==== 客户端错误段 ==========

    BAD_REQUEST(400, "请求参数不正确"),
    UNAUTHORIZED(401, "账号未登录"),
    FORBIDDEN(403, "没有该操作权限"),
    NOT_FOUND(404, "请求未找到"),
    METHOD_NOT_ALLOWED(405, "请求方法不正确"),
    LOCKED(423, "请求失败，请稍后重试"), // 并发请求，不允许
    TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),

//    ==== 服务端错误段 ==========

    INTERNAL_SERVER_ERROR(500, "系统异常"),
    NOT_IMPLEMENTED(501, "功能未实现/未开启"),
    ERROR_CONFIGURATION(502, "错误的配置项"),

    //    ==== 自定义错误段 ==========
    DEMO_DENY(901, "演示模式，禁止写操作"),

    UNKNOWN(999, "未知错误"),

    // ========== AUTH 模块 1-002-000-000 ==========
    AUTH_LOGIN_BAD_CREDENTIALS(1_002_000_000, "登录失败，账号密码不正确"),

    AUTH_LOGIN_USER_DISABLED(1_002_000_001, "登录失败，账号被禁用"),
    AUTH_LOGIN_USERNAME_OR_PASSWORD_EMPTY(1_002_000_002, "账号或密码不能为空"),

    AUTH_LOGIN_CAPTCHA_CODE_ERROR(1_002_000_004, "验证码不正确"),

    AUTH_THIRD_LOGIN_NOT_BIND(1_002_000_005, "未绑定账号，需要进行绑定"),

    AUTH_TOKEN_EXPIRED(1_002_000_006, "Token 已经过期"),

    AUTH_MOBILE_NOT_EXISTS(1_002_000_007, "手机号不存在"),

    AUTH_NO_USER(1_002_000_008, "用户不存在"),

    AUTH_USER_EXIST(1_002_000_009, "用户已存在");

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

}
