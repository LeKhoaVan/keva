package it.ktpm.keva.project.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.ktpm.keva.common.util.ResponseUtils;
import it.ktpm.keva.project.dto.WorkDTO;
import it.ktpm.keva.project.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work")
public class WorkController {
    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/getAll")
    public Object getAll(){
        return ResponseUtils.get(workService.getAll(), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/save")
    public Object saveWork(@RequestBody WorkDTO workDTO){
        return ResponseUtils.get(workService.saveWork(workDTO),HttpStatus.CREATED);
    }
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/temporary-work")
    public Object temporaryWork(@RequestParam("code") String code){
        return ResponseUtils.get(workService.temporaryWork(code),HttpStatus.OK);
    }
}
