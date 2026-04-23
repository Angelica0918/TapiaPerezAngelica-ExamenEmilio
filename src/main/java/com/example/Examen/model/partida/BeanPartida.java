package com.example.Examen.model.partida;

import com.example.Examen.model.jugador.BeanJugador;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "partida")
public class BeanPartida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private PartidaENUM partidaENUM;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private BeanJugador jugador;

    private double apuesta;

}


