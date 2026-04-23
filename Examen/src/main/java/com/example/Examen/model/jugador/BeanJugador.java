package com.example.Examen.model.jugador;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jugador")
public class BeanJugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String nombre;
    private double saldo;
    private boolean activo;

}








