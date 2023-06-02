package it.ktpm.keva.common.util;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class ExceptionUtils {
    private ExceptionUtils(){

    }
    //get exception @Length, @Size, @notBlank
    public static List<String> getError(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(excep -> excep.getMessage())
                .collect(Collectors.toList());
    }

    //get exception unique code
    public static List<String> getErrors(RuntimeException exception){
        return List.of("Ops! Something wrong happens...");
    }

    public static List<String> getErrors(MethodArgumentNotValidException exception) {
        return exception.getAllErrors().stream()
                .map(excep -> excep.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
