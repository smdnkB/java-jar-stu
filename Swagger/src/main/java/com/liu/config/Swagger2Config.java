package com.liu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.PushBuilder;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Config {


    // 可以配置多个Docket对象
    @Bean
    public Docket docketA(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("A");
    }

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否启动浏览器访问API页面
                .enable(true)
                .groupName("B")
                .select()
                // basePackage() 基于包去扫描
//                .apis(RequestHandlerSelectors.basePackage("com.liu.controller"))
                .apis(RequestHandlerSelectors.any())
                // 过滤哪些路径
                .paths(PathSelectors.none())
//                .paths(PathSelectors.ant("/**"))
                .build();
    }



    private ApiInfo apiInfo(){
        Contact contact = new Contact("111", "222", "333");
        return new ApiInfo(
                "Api Documentation",
                "Api Documentation",
                "1.0",
                "urn:tos", contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}

