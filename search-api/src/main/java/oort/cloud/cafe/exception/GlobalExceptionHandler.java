package oort.cloud.cafe.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import oort.cloud.cafe.data.ExceptionResponse;
import oort.cloud.cafe.exception.ErrorType;
import oort.cloud.cafe.exception.NaverApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NaverApiException.class)
    public ResponseEntity<ExceptionResponse> handleNaverApiException(NaverApiException e){
        log.error("[Naver API] errorMessage : {} className : {}", e.getErrorMessage(), e.getClass().getName());
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ExceptionResponse(e.getErrorMessage(), e.getErrorType()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleApiParameterException(MethodArgumentNotValidException e){
        log.error("[API Request] errorMessage : {} className : {}", e.getMessage(), e.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(getErrorFieldMessage(e), ErrorType.INVALID_PARAMETER));
    }

    private String getErrorFieldMessage(MethodArgumentNotValidException e){
        if(e.getFieldError() != null && e.getFieldError().getDefaultMessage() != null){
            return e.getFieldError().getDefaultMessage();
        }
        return e.getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", ")) + "정의 되지않음";
    }

}
