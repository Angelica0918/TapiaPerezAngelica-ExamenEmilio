package com.example.Examen.model.partida;

import com.example.Examen.model.jugador.BeanJugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PartidaRepository extends JpaRepository<BeanPartida, Long> {
    List<BeanPartida> findByJugadorId(Long jugadorId);
}