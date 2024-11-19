package com.example.project_ci_cd.repository;

import com.example.project_ci_cd.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    Boolean existsByPlacaIgnoreCase(String placa);

    Carro findByPlacaIgnoreCaseAndIdNot(String placa, Integer id);

}