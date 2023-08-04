package com.example.ingerencia.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ingerencia.models.LogModel;

public interface LogRepository extends CrudRepository<LogModel, Long>{
    
}
