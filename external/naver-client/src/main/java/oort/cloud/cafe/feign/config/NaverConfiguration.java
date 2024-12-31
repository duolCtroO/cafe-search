package oort.cloud.cafe.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class NaverConfiguration {

    @Bean
    RequestInterceptor requestInterceptor(
            @Value("${external.naver.headers.client-id}") String clientId,
            @Value("${external.naver.headers.client-secret}") String clientSecret
            ){
        return template -> {
            template.header("X-Naver-Client-Id", clientId);
            template.header("X-Naver-Client-Secret", clientSecret);
        };
    }

    @Bean
    NaverErrorDecoder naverErrorDecoder(ObjectMapper objectMapper){
        return new NaverErrorDecoder(objectMapper);
    }
}
