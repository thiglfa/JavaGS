package com.example.mottu_springboot.repository;

import com.example.mottu_springboot.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long usuarioId);
    List<Reserva> findByMotoId(Long motoId);
}

