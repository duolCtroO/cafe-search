package oort.cloud.cafe.exception;


import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Getter
@ToString
public class NaverApiException extends RuntimeException{
    private final String errorMessage;
    private final ErrorType errorType;
    private final HttpStatus httpStatus;

    public NaverApiException(String errorMessage, ErrorType errorType, HttpStatus httpStatus){
        this.errorMessage = errorMessage;
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }

}
