package it.ktpm.keva.project.service;

import it.ktpm.keva.project.dto.ProjectDTO;
import it.ktpm.keva.project.model.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ProjectDTO saveProject(ProjectDTO projectDTO);
    ProjectDTO temporaryProject(String code);

    public List<ProjectDTO> findAll();

    Project findById(UUID project);
}
