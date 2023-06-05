package it.ktpm.keva.role.service.impl;

import it.ktpm.keva.common.util.KevaMapper;
import it.ktpm.keva.role.dto.OperationDTO;
import it.ktpm.keva.role.model.Operation;
import it.ktpm.keva.role.repository.OperationRepository;
import it.ktpm.keva.role.service.OperationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class OperationServiceImpl implements OperationService {
    private final KevaMapper mapper;
    private final OperationRepository operationRepository;

    public OperationServiceImpl(KevaMapper mapper, OperationRepository operationRepository) {
        this.mapper = mapper;
        this.operationRepository = operationRepository;
    }

    @Override
    public JpaRepository<Operation, UUID> getRepository() {
        return this.operationRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }


    @Override
    public OperationDTO saveOperation(OperationDTO operationDTO) {
        Operation operation = mapper.map(operationDTO,Operation.class);
        Operation saveOperation = operationRepository.save(operation);
        OperationDTO dto = mapper.map(saveOperation, OperationDTO.class);
        return dto;
    }

    @Override
    public List<Operation> findAll(List<UUID> operationIds) {
        return operationRepository.findAllById(operationIds);
    }


}
