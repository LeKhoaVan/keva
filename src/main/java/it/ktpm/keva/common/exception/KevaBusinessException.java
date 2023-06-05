package it.ktpm.keva.common.exception;

public class KevaBusinessException extends RuntimeException{

    public KevaBusinessException(String userIsNotExisted) {
        super(userIsNotExisted);
    }
}
