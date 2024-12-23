package oort.cloud.cafe.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import oort.cloud.cafe.data.NaverErrorResponse;
import oort.cloud.cafe.exception.NaverApiException;

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
            throw new NaverApiException(naverErrorResponse.getErrorMessage(), naverErrorResponse.getErrorCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
