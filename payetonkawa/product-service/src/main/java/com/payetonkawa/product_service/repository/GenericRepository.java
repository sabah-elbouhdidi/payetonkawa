package com.payetonkawa.product_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<Entity> extends CrudRepository<Entity, Long> {

}