package cn.mono.purchase.exception;

import cn.mono.purchase.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : 秋雨
 * @since : 2024/02/04
 * Description  :
 */
@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Message error(ServiceException e) {
        log.error("自定义异常:  {}", e.getMessage());
        return Message.error( e.getErrorCodeEnum());
    }

    @ExceptionHandler(Exception.class)
    public Message error(Exception e) {
        log.error("未知异常:  {}", e.getMessage());
        return Message.error(e.getMessage());
    }

}
