package oort.cloud.cafe.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.Response;
import oort.cloud.cafe.exception.ErrorType;
import oort.cloud.cafe.exception.NaverApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        assertThatThrownBy(
                () -> {
                    naverErrorDecoder.decode("GET", response);
                }).hasMessage("test");

    }

    @Test
    void 네이버_API_커스텀_예외_테스트(){
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
        NaverApiException exception = Assertions.assertThrows(
                NaverApiException.class,
                () -> naverErrorDecoder.decode("GET", response)
        );

        assertThat(exception.getErrorType()).isEqualTo(ErrorType.EXTERNAL_API_ERROR);
        assertThat(exception.getErrorMessage()).isEqualTo("test");
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

}