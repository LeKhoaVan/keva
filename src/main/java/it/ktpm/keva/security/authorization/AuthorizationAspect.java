package it.ktpm.keva.security.authorization;

import it.ktpm.keva.common.exception.KevaBusinessException;
import it.ktpm.keva.role.model.Operation;
import it.ktpm.keva.role.service.OperationService;
import it.ktpm.keva.security.validation.annotation.KevaOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Aspect
@Component
@Slf4j
public class AuthorizationAspect {
    private final OperationService operationService;

    public AuthorizationAspect(OperationService operationService) {
        this.operationService = operationService;
    }

    /**
     *
     * @param kevaOperation la 1 annotation
     *
     * điểm xét (point join) là controller
     * winthin la da vao ben trong cua lop. Example: "within(it.ktpm.keva.role.controller.*)"
     * hoac dung @annotation linh hoat hon
     * danh dau annotation KevaOperation
     */

    @Before("@annotation(kevaOperation)")
    public void authorizationOperation(KevaOperation kevaOperation){
        log.info("Point has been actived "+kevaOperation.name());

        //get user current
        String userName = getUserName();

        //check operation
        if(!permtited(kevaOperation.name(),userName)){
            throw new KevaBusinessException("User is not permitted");
        }
    }

    private boolean permtited(String operationName, String userName) {
        List<Operation> operations = operationService.findAllByNameAndUserName(operationName, userName);
        return !operations.isEmpty();
    }

    private String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null){
            return null;
        }
        if(authentication.getPrincipal() instanceof String){
            return (String) authentication.getPrincipal();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
