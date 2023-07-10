package it.ktpm.keva.project.service.impl;

import it.ktpm.keva.common.util.KevaMapper;
import it.ktpm.keva.project.dto.WorkDTO;
import it.ktpm.keva.project.model.Project;
import it.ktpm.keva.project.model.Work;
import it.ktpm.keva.project.repository.WorkRepository;
import it.ktpm.keva.project.service.ProjectService;
import it.ktpm.keva.project.service.WorkService;
import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;
    private final UserService userService;
    private final ProjectService projectService;
    private final KevaMapper mapper;

    public WorkServiceImpl(WorkRepository workRepository, UserService userService, ProjectService projectService, KevaMapper mapper) {
        this.workRepository = workRepository;
        this.userService = userService;
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @Override
    public WorkDTO saveWork(WorkDTO workDTO) {
        Work work = mapper.map(workDTO,Work.class);
        User user = userService.findById(workDTO.getIdUser());
        Project project = projectService.findById(workDTO.getIdProject());
        work.setUser(user);
        work.setProject(project);

        user.addWork(work);
        project.addWork(work);

        Work workSave = workRepository.save(work);
        workDTO.setIdUser(workSave.getUser().getId());
        workDTO.setIdProject(workSave.getProject().getId());

        return workDTO;
    }

    @Override
    public WorkDTO temporaryWork(String code) {
        Work work = workRepository.findCode(code);
        if(work != null){
            Work workTemporary = workRepository.changeStatus(code, Work.Status.TEMPORARY_BLOCKED);
            return mapper.map(workTemporary,WorkDTO.class);
        }
        return null;
    }

    @Override
    public List<WorkDTO> getAll() {
        List<WorkDTO> workDTOs = workRepository.findAll().stream()
                .map(work -> {
                    WorkDTO workDTO = mapper.map(work,WorkDTO.class);
                    workDTO.setIdProject(work.getProject().getId());
                    workDTO.setIdUser(work.getUser().getId());
                return workDTO;})
                .collect(Collectors.toList());
        return workDTOs;
    }

    @Override
    public WorkDTO completeWork(String code) {
        Work work = workRepository.findCode(code);
        if(work != null){
            Work workTemporary = workRepository.changeStatus(code,Work.Status.COMPLETE);
            return mapper.map(workTemporary,WorkDTO.class);
        }
        return null;
    }
}
