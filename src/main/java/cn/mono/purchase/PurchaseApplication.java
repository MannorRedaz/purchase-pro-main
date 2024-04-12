package cn.mono.purchase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : 秋雨
 * @since : 2024/04/10
 * Description  :
 */
@SpringBootApplication
//@MapperScan("cn.mono.purchase.mapper")
public class PurchaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(PurchaseApplication.class,args);
    }
}
