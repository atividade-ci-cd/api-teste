package com.example.project_ci_cd.service;

import com.example.project_ci_cd.entity.Carro;
import com.example.project_ci_cd.repository.CarroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;

    @Transactional
    public Carro cadastrar(Carro entity) {
        if (carroRepository.existsByPlacaIgnoreCase(entity.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return carroRepository.save(entity);
    }

    public Carro buscarPorId(int id) {
        return carroRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Carro editar(int id, Carro entity) {
        if (!carroRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Carro carroByPlaca = carroRepository.findByPlacaIgnoreCaseAndIdNot(entity.getPlaca(), id);

        if (carroByPlaca != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        entity.setId(id);
        return carroRepository.save(entity);

    }

    public void deletar(int id) {
        if (!carroRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        carroRepository.deleteById(id);
    }
}
