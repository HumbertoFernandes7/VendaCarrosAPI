package com.github.humbertofernandes7.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.humbertofernandes7.carros.entites.CarroEntity;

public interface CarroRepository extends JpaRepository<CarroEntity, Long>  {

}
