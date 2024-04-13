package cn.mono.purchase.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : 秋雨
 * @since : 2024/01/22
 * Description  : 业务逻辑异常 Exception
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class ServiceException extends RuntimeException{

    private Integer code;
    private String message;
    private ErrorCodeEnum errorCodeEnum;

    public ServiceException(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMsg();
    }
}
