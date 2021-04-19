package com.inter.dio.heroisapi.repository;


import com.inter.dio.heroisapi.model.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String> {
}
