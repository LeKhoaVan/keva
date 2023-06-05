package it.ktpm.keva.common.exception;

import it.ktpm.keva.common.models.ResponseDTO;
import it.ktpm.keva.common.util.ResponseUtils;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalException {

    // exception khi luu xuong database
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> getError(ConstraintViolationException exception){
        return ResponseUtils.error(exception,HttpStatus.BAD_REQUEST);
    }

    // khong lay runtimeException vi la lop cha cua ConstraintViolationException
    // nen khi co runtime thi ConstraintViolationException khong hoat dong
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<ResponseDTO> getError(RuntimeException exception){
//        return ResponseUtils.error(exception,HttpStatus.BAD_REQUEST);
//    }

    //exception KevaBusinessException
    @ExceptionHandler(KevaBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> getError(KevaBusinessException exception){
        return ResponseUtils.error(exception,HttpStatus.BAD_REQUEST);
    }

    //exception phia truoc, phia DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> getError(MethodArgumentNotValidException exception){
        return ResponseUtils.error(exception,HttpStatus.BAD_REQUEST);
    }

}
