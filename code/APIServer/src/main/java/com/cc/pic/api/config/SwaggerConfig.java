package com.cc.pic.api.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProJectName APIServer
 * @FileName SwaggerConfig
 * @Description Swagger 配置
 * @Author CandyMuj
 * @Date 2019/12/23 10:45
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        List<Parameter> parameterList = new ArrayList<>();
        tokenBuilder.name("Authorization")
                .defaultValue("去其他请求中获取heard中token参数")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();
        parameterList.add(tokenBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API By CandyMuj")
                .description("旅行风光-API")
                .termsOfServiceUrl("")
                .contact(new Contact("CandyMuj", "www.candymuj.cc", "if@mj-mail.cc"))
                .version("1.0")
                .build();
    }

}
