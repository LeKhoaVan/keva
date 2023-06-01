package it.ktpm.keva.common.service;

import it.ktpm.keva.common.models.EntityBase;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericService<T extends EntityBase, ID, DTO>{
    JpaRepository<T,ID> getRepository();
    ModelMapper getMapper();

    default List<T> findAll(){
        return getRepository().findAll();
    }
    default List<T> findAll(Pageable page){
        return getRepository().findAll(page)
                .stream().collect(Collectors.toList());
    }

    default List<DTO> findAllDto(Class<DTO> clazz){
        return getRepository().findAll().stream()
                .map(model -> getMapper().map(model,clazz))
                .collect(Collectors.toList());
    }
    default List<DTO> findAllDto(Class<DTO> clazz,Pageable page){
        return getRepository().findAll(page).stream()
                .map(model -> getMapper().map(model,clazz))
                .collect(Collectors.toList());
    }

    @Transactional
    default T save(T entity){
        return getRepository().save(entity);
    }
    @Transactional
    default T update(T entity){
        return getRepository().save(entity);
    }

    @Transactional
    default T deleteById(ID id){
        T entity = getRepository().findById(id).orElse(null);
        if(entity != null){
            getRepository().delete(entity);
        }
        return entity;
    }

}
