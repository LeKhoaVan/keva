package it.ktpm.keva.common.util;

import it.ktpm.keva.common.models.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
}
