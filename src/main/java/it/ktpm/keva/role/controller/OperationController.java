package it.ktpm.keva.role.controller;

import it.ktpm.keva.common.util.ResponseUtils;
import it.ktpm.keva.role.dto.OperationDTO;
import it.ktpm.keva.role.service.OperationService;
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

    @GetMapping("/findAll")
    public Object findAll(){
        return ResponseUtils.get(operationService.findAllDto(OperationDTO.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object saveOperation(@RequestBody @Valid OperationDTO operationDTO){
        return ResponseUtils.get(operationService.saveOperation(operationDTO),HttpStatus.CREATED);
    }
}
