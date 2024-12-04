package com.payetonkawa.product_service.service;

import java.util.List;

public interface GenericService<DTO> {
    DTO get(Long id) throws Exception;
    List<DTO> getAll() throws Exception;
    void delete(Long id) throws Exception;
    void add(DTO dto) throws Exception;
    void addList(List<DTO> dtoList) throws Exception;
}
