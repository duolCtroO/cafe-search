package oort.cloud.cafe.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class NaverErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    public NaverErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String body = new String(response.body().asInputStream().readAllBytes());
            NaverErrorResponse naverErrorResponse = objectMapper.readValue(body, NaverErrorResponse.class);
            throw new RuntimeException(naverErrorResponse.getErrorMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
