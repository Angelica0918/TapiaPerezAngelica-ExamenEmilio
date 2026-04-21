package com.example.Examen.model.tiro;


import com.example.Examen.model.partida.BeanPartida;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tiro")
public class BeanTiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private BeanPartida partida;

    private double valorDado1;
    private double valorDado2;

    private double suma;
    private boolean esGanador;

}
