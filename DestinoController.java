package com.agencia.viagens.controller;

import com.agencia.viagens.entity.Destino;
import com.agencia.viagens.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private DestinoRepository destinoRepository;

    @GetMapping
    public List<Destino> listar() {
        return destinoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Destino> criar(@RequestBody Destino destino) {
        Destino novoDestino = destinoRepository.save(destino);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDestino);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        destinoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}