package com.example.project_ci_cd.controller;

import com.example.project_ci_cd.dto.CarroCriacaoDto;
import com.example.project_ci_cd.entity.Carro;
import com.example.project_ci_cd.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carros")
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    public ResponseEntity<Carro> cadastrarCarro(@RequestBody CarroCriacaoDto carroCriacaoDto) {
        Carro entity = toEntity(carroCriacaoDto);

        Carro carroSalvo = carroService.cadastrar(entity);
        URI uri = URI.create("/carros/" + carroSalvo.getId());

        return ResponseEntity.created(uri).body(carroSalvo);
    }

    public static Carro toEntity(CarroCriacaoDto dto) {
        Carro carro = new Carro();
        carro.setCor(dto.getCor());
        carro.setMarca(dto.getMarca());
        carro.setModelo(dto.getModelo());
        carro.setPlaca(dto.getPlaca());

        return carro;
    }

    @GetMapping
    public ResponseEntity<List<Carro>> listarTodos() {
        List<Carro> entities = carroService.listarTodos();

        if (entities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(carroService.buscarPorId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Carro> editar(@PathVariable int id, @RequestBody CarroCriacaoDto carroAtualizado) {
        Carro entity = toEntity(carroAtualizado);

        Carro carroSalvo = carroService.editar(id, entity);

        if (carroSalvo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(carroSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        carroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}