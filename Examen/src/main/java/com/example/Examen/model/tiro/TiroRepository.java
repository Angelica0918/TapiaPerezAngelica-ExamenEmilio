package com.example.Examen.model.tiro;

import com.example.Examen.model.jugador.BeanJugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiroRepository extends JpaRepository<BeanTiro, Long> {
}
