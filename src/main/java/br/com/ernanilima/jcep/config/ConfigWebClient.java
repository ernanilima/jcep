package br.com.ernanilima.jcep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClient {

    /**
     * Consumir o ViaCep
     * @param builder WebClient.Builder
     * @return WebClient
     */
    @Bean
    public WebClient webClientZipCode(WebClient.Builder builder) {
        return builder
                .baseUrl("https://viacep.com.br/ws/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
