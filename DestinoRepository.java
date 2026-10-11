package com.agencia.viagens.repository;

import com.agencia.viagens.entity.Destino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
}