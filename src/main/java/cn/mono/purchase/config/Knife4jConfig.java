package cn.mono.purchase.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 秋雨
 * @since : 2024/02/04
 * Description  :
 */
@Configuration
public class Knife4jConfig {


    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin-api")
                .pathsToMatch("/**")
                .build();
    }

//    @Bean
//    public GroupedOpenApi frontApi() {
//        return GroupedOpenApi.builder()
//                .group("front-api")
//                .pathsToMatch("/api/**")
//                .build();
//    }


    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("API接口文档")
                        .version("1.0")
                        .description("API接口文档")
                        .contact(new Contact().name("秋雨")));
    }
}
