package in.co.madguy.springbootpoc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("SpringBoot POC Api")
            .version("1.0.0")
            .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("in.co.madguy.springbootpoc.controller"))
            .paths(regex("/.*"))
            .build()
            .apiInfo(apiInfo());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * Method to register HTTP Message Converters
     * Note: Gson can be alternatively registered from application.properties
     * Refer:
     * spring.http.converters.preferred-json-mapper=gson
     * spring.gson.date-format=YYYY-MM-DD'T'HH:mm:ss
     *
     * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html">Spring application properties</a>
     * @see <a href="https://www.callicoder.com/configuring-spring-boot-to-use-gson-instead-of-jackson/">Blog</a>
     *
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Using Java Config: Register custom HTTP Message Converters
        /*Gson gson = new GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
            .create();
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson);
        converters.add(gsonHttpMessageConverter);
        super.configureMessageConverters(converters);*/
    }
}
