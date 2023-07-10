package it.ktpm.keva.project.service.impl;

import it.ktpm.keva.common.exception.KevaBusinessException;
import it.ktpm.keva.common.util.KevaMapper;
import it.ktpm.keva.project.dto.ProjectDTO;
import it.ktpm.keva.project.model.Project;
import it.ktpm.keva.project.repository.ProjectRepository;
import it.ktpm.keva.project.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final KevaMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, KevaMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        Project project = mapper.map(projectDTO,Project.class);
        Project projectSave = projectRepository.save(project);
        ProjectDTO dto = mapper.map(projectSave,ProjectDTO.class);
        return dto;
    }

    @Override
    public ProjectDTO temporaryProject(String code) {
        Project project = projectRepository.handleFindByCode(code);
        if(project !=null) {
            projectRepository.changeStatusProject(project.getCode(), Project.Status.TEMPORARY_BLOCKED);
            ProjectDTO dto = mapper.map(project,ProjectDTO.class);
            dto.setStatus(Project.Status.TEMPORARY_BLOCKED);
            return dto;
        }
        return null;
    }

    @Override
    public ProjectDTO compeleteProject(String code) {
        Project project = projectRepository.handleFindByCode(code);
        if(project !=null) {
            if (checkComplete(project.getCode()) == null){
                projectRepository.changeStatusProject(project.getCode(), Project.Status.COMPLETE);
                ProjectDTO dto = mapper.map(project,ProjectDTO.class);
                dto.setStatus(Project.Status.COMPLETE);
                return dto;
            }
        }
        return null;
    }

    @Override
    public List<ProjectDTO> findAll() {
        List<Project> projects = projectRepository.findAll();

        List<ProjectDTO> projectDTOS = projects.stream()
                .map(project -> mapper.map(project,ProjectDTO.class))
                .collect(Collectors.toList());
        return projectDTOS;
    }

    @Override
    public Project findById(UUID project) {
        return projectRepository.findById(project).orElse(null);
    }

    @Override
    public Project checkComplete(String codeProject) {
        if (projectRepository.checkCompleteWork(codeProject) != null){
            throw new KevaBusinessException("cannot complete project because work is not complete");
        }
        return projectRepository.checkCompleteWork(codeProject);
    }

}
