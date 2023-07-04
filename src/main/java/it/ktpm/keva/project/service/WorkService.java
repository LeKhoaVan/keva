package it.ktpm.keva.project.service;

import it.ktpm.keva.project.dto.WorkDTO;

import java.util.List;

public interface WorkService {
    WorkDTO saveWork(WorkDTO workDTO);
    WorkDTO temporaryWork(String code);
    List<WorkDTO> getAll();
}
