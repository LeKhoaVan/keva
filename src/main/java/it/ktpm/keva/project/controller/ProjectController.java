package it.ktpm.keva.project.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.ktpm.keva.common.util.ResponseUtils;
import it.ktpm.keva.project.dto.ProjectDTO;
import it.ktpm.keva.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getAll")
    @SecurityRequirement(name = "bearerAuth")
    public Object getAll(){
        return ResponseUtils.get(projectService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/save")
    @SecurityRequirement(name = "bearerAuth")
    public Object saveProject(@RequestBody ProjectDTO projectDTO){
        return ResponseUtils.get(projectService.saveProject(projectDTO), HttpStatus.CREATED);
    }

    @PutMapping("temporary-blocked")
    @SecurityRequirement(name = "bearerAuth")
    public Object temporaryProject(@RequestParam("code") String code){
        return ResponseUtils.get(projectService.temporaryProject(code),HttpStatus.OK);
    }

    @PutMapping("complete-project")
    @SecurityRequirement(name = "bearerAuth")
    public Object completeProject(@RequestParam("code") String code){
        return ResponseUtils.get(projectService.compeleteProject(code),HttpStatus.OK);
    }
}
