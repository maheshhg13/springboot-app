package asapp.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication
public class ChatApiApp {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ChatApiApp.class, args);
	}
	
	@Bean
    public Docket chatApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("/api/.*"))
                .build().useDefaultResponseMessages(false);
    }
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ASAPP Chat")
                .description("This is a set of User Chat APIs created for ASAPP's backend project.")
                .version("1.0")
                .build();
    }
}