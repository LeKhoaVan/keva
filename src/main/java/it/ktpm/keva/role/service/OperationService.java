package it.ktpm.keva.role.service;

import it.ktpm.keva.common.service.GenericService;
import it.ktpm.keva.role.dto.OperationDTO;
import it.ktpm.keva.role.model.Operation;

import java.util.List;
import java.util.UUID;

public interface OperationService extends GenericService<Operation, UUID, OperationDTO> {
    OperationDTO saveOperation(OperationDTO operationDTO);
    List<Operation> findAll(List<UUID> operationIds);


}
