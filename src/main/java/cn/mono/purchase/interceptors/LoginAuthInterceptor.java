package cn.mono.purchase.interceptors;

import cn.hutool.core.util.StrUtil;
import cn.mono.purchase.dto.Message;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import static cn.mono.purchase.constants.Constants.ADMIN_TOKEN_PREFIX;
import static cn.mono.purchase.constants.Constants.TOKEN_EXPIRE_TIME;

/**
 * @author : 秋雨
 * @since : 2024/04/12
 * Description  :
 */
@Component
@Slf4j
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }
        String sysUserInfoJson = redisTemplate.opsForValue().get(ADMIN_TOKEN_PREFIX+ token);
        if (StrUtil.isEmpty(sysUserInfoJson)) {
            responseNoLoginInfo(response);
            return false;
        }
        // 重置Redis中的用户数据的有效时间
        redisTemplate.expire(ADMIN_TOKEN_PREFIX + token, TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        return true;
    }


    private void responseNoLoginInfo(HttpServletResponse response) {

        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        Message message = new Message();
        message.setMsg("你的账号未登录，请先登录");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(message));
        } catch (IOException e) {
            log.error("出错啦，未登录:IOException{}  ", e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
