package com.example.ingerencia.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ingerencia.models.BeerModel;

@Repository
public interface BeerRepository extends CrudRepository<BeerModel, Long>{
    public abstract ArrayList<BeerModel> findByvName(String name);

    @Query(value = "SELECT * FROM beer ORDER BY RAND() LIMIT 1;", nativeQuery = true)
    public ArrayList<BeerModel> findByRandom();
}
