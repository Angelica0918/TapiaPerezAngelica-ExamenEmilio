package com.example.Examen.service.jugador;

import com.example.Examen.model.jugador.BeanJugador;
import com.example.Examen.model.jugador.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;


    public BeanJugador recargarSaldo(Long jugadorId, double monto) {
        BeanJugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        jugador.setSaldo(jugador.getSaldo() + monto);
        return jugadorRepository.save(jugador);
    }
}





