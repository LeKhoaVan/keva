package it.ktpm.keva.role.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.ktpm.keva.common.util.ResponseUtils;
import it.ktpm.keva.role.dto.OperationDTO;
import it.ktpm.keva.role.model.Operation;
import it.ktpm.keva.role.service.OperationService;
import it.ktpm.keva.security.validation.annotation.KevaOperation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService){
        this.operationService = operationService;
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/findAll")
    @KevaOperation(name = "findAllOperation", type= Operation.Type.FETCH)
    public Object findAll(){
        return ResponseUtils.get(operationService.findAllDto(OperationDTO.class), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/save")
    public Object saveOperation(@RequestBody @Valid OperationDTO operationDTO){
        return ResponseUtils.get(operationService.saveOperation(operationDTO),HttpStatus.CREATED);
    }
}
