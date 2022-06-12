package br.com.ernanilima.jcep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigSwagger {

    /**
     * Documentacao para o front-end consumir o servico/api
     * @return Docket
     */
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2) // tipo de documentacao que sera exibida para o front-end
                .select()
                .apis(RequestHandlerSelectors.any()) // pacato de acesso ex: br.com.ernanilima.jcep
                .paths(PathSelectors.regex("/endereco.*")) // rota liberada para documentacao
                .build();
    }
}
