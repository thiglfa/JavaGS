package com.example.mottu_springboot.repository;


import com.example.mottu_springboot.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    // Buscar motos por status (ex: "DISPONIVEL", "RESERVADA", "MANUTENCAO")
    List<Moto> findByStatus(String status);

    // Buscar motos por modelo
    List<Moto> findByModeloContainingIgnoreCase(String modelo);

    // Buscar motos por ano
    List<Moto> findByAno(int ano);

}

