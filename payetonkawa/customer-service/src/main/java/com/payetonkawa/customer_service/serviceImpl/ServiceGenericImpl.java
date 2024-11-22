package com.payetonkawa.customer_service.serviceImpl;

import com.payetonkawa.customer_service.repository.GenericRepository;
import com.payetonkawa.customer_service.service.GenericService;
import com.payetonkawa.customer_service.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceGenericImpl <DTO,Entity> implements GenericService<DTO> {

    @Autowired
    private GenericRepository<Entity> genericRepository;
    @Autowired
    private Transformer<DTO, Entity> transformer;

    @Override
    public DTO get(Long id) throws Exception {
        try {
            return transformer.fromEntityToDto((genericRepository.findById(id)).get());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<DTO> getAll() throws Exception {
        return transformer.fromEntityListToDtoList((List<Entity>) genericRepository.findAll());
    }

    @Override
    public void add(DTO dto) throws Exception {
        genericRepository.save(transformer.fromDtoToEntity(dto));
    }

    @Override
    public void addList(List<DTO> DTOList) throws Exception {
        for (DTO dto : DTOList) {
            this.add(dto);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        genericRepository.deleteById(id);
    }


}
