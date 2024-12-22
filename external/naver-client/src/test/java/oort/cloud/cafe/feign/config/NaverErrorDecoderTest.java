package oort.cloud.cafe.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NaverErrorDecoderTest {

    @Test
    void 에러디코더_ErrorResponse_에러메세지_확인(){
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        NaverErrorDecoder naverErrorDecoder = new NaverErrorDecoder(objectMapper);

        //when
        String errorMessage = "{\"errorMessage\": \"test\"}";
        Response response = Response.builder()
                .status(400)
                .reason("Bad Request")
                .request(Request.create(Request.HttpMethod.GET, "", Collections.emptyMap(), null, StandardCharsets.UTF_8, null))
                .body(errorMessage, StandardCharsets.UTF_8)
                .headers(Collections.emptyMap())
                .build();

        //then
        Assertions.assertThatThrownBy(
                () -> {
                    naverErrorDecoder.decode("GET", response);
                }).hasMessage("test");

    }

}