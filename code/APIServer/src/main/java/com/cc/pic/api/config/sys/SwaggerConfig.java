package com.cc.pic.api.config.sys;

import com.cc.pic.api.annotations.ApiVersion;
import com.cc.pic.api.config.SecurityConstants;
import com.cc.pic.api.enumc.ApiGroup;
import com.google.common.base.Optional;
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
import java.util.Arrays;
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

    /**
     * 将所有的接口都加入到默认分组中
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(this.globalParameters());
    }

    @Bean
    public Docket admin() {
        return this.buildWithGroup(ApiGroup.ADMIN);
    }


    /**
     * 通过分组注解配置，生成docket
     */
    private Docket buildWithGroup(ApiGroup apiGroup) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .groupName(apiGroup.getName())
                .select()
                .apis(input -> {
                    if (input != null && input.isAnnotatedWith(ApiOperation.class)) {
                        // 如果方法和类上同时存在注解，即以方法上的注解为准
                        // 先获取方法上的分组信息
                        Optional<ApiVersion> optional = input.findAnnotation(ApiVersion.class);
                        if (optional.isPresent()) {
                            return Arrays.asList(optional.get().value()).contains(apiGroup);
                        }

                        // 然后获取Controller类上的分组信息
                        optional = input.findControllerAnnotation(ApiVersion.class);

                        return optional.isPresent() && Arrays.asList(optional.get().value()).contains(apiGroup);
                    }

                    return false;
                })
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(this.globalParameters());
    }

    private List<Parameter> globalParameters() {
        List<Parameter> parameterList = new ArrayList<>();

        ParameterBuilder tokenBuilder = new ParameterBuilder();
        tokenBuilder.name(SecurityConstants.REQ_HEADER)
                .defaultValue("去其他请求中获取heard中token参数")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();

        parameterList.add(tokenBuilder.build());

        return parameterList;
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
