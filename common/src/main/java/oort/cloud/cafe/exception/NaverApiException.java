package oort.cloud.cafe.exception;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NaverApiException extends RuntimeException{
    private final String errorMessage;
    private final String errorCode;

    public NaverApiException(String errorMessage, String errorCode){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

}
