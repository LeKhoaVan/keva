package it.ktpm.keva.common.util;

import it.ktpm.keva.common.models.ResponseDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;


public class ResponseUtils {
    private ResponseUtils(){
    }

    public static ResponseEntity<ResponseDTO> get(Object content, HttpStatus status){
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(content)
                        .hasError(false)
                        .error(Collections.emptyList())
                        .timestamp(DateTimeUtils.now())
                        .status(status.value())
                        .build()
                ,status);
    }

    public static ResponseEntity<ResponseDTO> error(ConstraintViolationException exception,
                                                    HttpStatus badRequest) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .error(ExceptionUtils.getError(exception))
                        .timestamp(DateTimeUtils.now())
                        .status(badRequest.value())
                        .build()
                ,badRequest);
    }
    public static ResponseEntity<ResponseDTO> error(RuntimeException exception, HttpStatus badRequest){
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .error(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .status(badRequest.value())
                        .build()
                ,badRequest);
    }

    public static ResponseEntity<ResponseDTO> error(MethodArgumentNotValidException exception,
                                                    HttpStatus badRequest) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasError(true)
                        .error(ExceptionUtils.getErrors(exception))
                        .timestamp(DateTimeUtils.now())
                        .status(badRequest.value())
                        .build()
                ,badRequest);
    }
}
