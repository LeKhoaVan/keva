package it.ktpm.keva.project.validation.implement;

import it.ktpm.keva.common.exception.KevaBusinessException;
import it.ktpm.keva.project.model.Project;
import it.ktpm.keva.project.service.ProjectService;
import it.ktpm.keva.project.validation.annotation.CheckCompleteWork;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CheckCompleteWorkAspect {
    private final ProjectService projectService;

    public CheckCompleteWorkAspect(ProjectService projectService){
        this.projectService = projectService;
    }

    /**
     * Điểm xét là trước khi thực thi controller
     * Kiểm tra các công việc đã hoàn thành
     */
    @Before("@annotation(checkCompleteWork)")
    public void checkCompleteWork(CheckCompleteWork checkCompleteWork){
        log.info("Poin check:"+checkCompleteWork.name());
//        Project project = projectService.checkComplete();
//
//        if(project != null){
//            throw new KevaBusinessException("cannot complete project, because work is not complete");
//        }
    }
}
