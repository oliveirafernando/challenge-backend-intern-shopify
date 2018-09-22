package ca.shopify.backend.challenge.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig { //extends WebMvcConfigurationSupport {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("ca.shopify.backend.challenge.controller"))
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.apiInfo(metaData());

	}
	
	private ApiInfo metaData(){

        Contact contact = new Contact(
        		"Fernando Oliveira", 
        		"http://oliveirafernando.com",
                "fernando.dsfw@gmail.com");

        return new ApiInfo(
                "Shopify Winter 2019 Developer Intern Challenge",
                "API Documentation",
                "1.0.0",
                "",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

//	@Override
//	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html")
//			.addResourceLocations("classpath:/META-INF/resources/");
//		
//		registry.addResourceHandler("/webjars/**")
//			.addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}
}
