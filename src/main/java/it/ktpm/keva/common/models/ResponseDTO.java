package it.ktpm.keva.common.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class ResponseDTO {
    private Object content;
    private boolean hasError;
    private List<String> error;
    private String timestamp;
    private int status;
}
