package oort.cloud.cafe.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import oort.cloud.cafe.data.NaverErrorResponse;
import oort.cloud.cafe.exception.ErrorType;
import oort.cloud.cafe.exception.NaverApiException;
import org.springframework.http.HttpStatus;

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
            throw new NaverApiException(naverErrorResponse.getErrorMessage(), ErrorType.EXTERNAL_API_ERROR, HttpStatus.valueOf(response.status()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
