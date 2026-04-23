package com.example.Examen.service.partida;

import com.example.Examen.model.jugador.BeanJugador;
import com.example.Examen.model.jugador.JugadorRepository;
import com.example.Examen.model.partida.BeanPartida;
import com.example.Examen.model.partida.PartidaENUM;
import com.example.Examen.model.partida.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartidaService {
    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private JugadorRepository jugadorRepository;


    public BeanPartida iniciarPartida(Long jugadorId, double apuesta) {


        BeanJugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new RuntimeException("jugador no encontrado"));

        if (!jugador.isActivo()) {
            throw new RuntimeException("el jugador no se encuentra activo");
        }

        if (jugador.getSaldo() < apuesta) {
            throw new RuntimeException("Saldo insuficiente,saldo actual: " + jugador.getSaldo());
        }

        jugador.setSaldo(jugador.getSaldo() - apuesta);
        jugadorRepository.save(jugador);

        BeanPartida partida = new BeanPartida();
        partida.setJugador(jugador);
        partida.setApuesta(apuesta);
        partida.setFecha(LocalDateTime.now());
        partida.setPartidaENUM(PartidaENUM.EN_JUEGO);

        return partidaRepository.save(partida);
    }

    public BeanPartida finalizarPartidaManual(Long partidaId) {
        BeanPartida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("no se encontro la partida"));

        if (partida.getPartidaENUM() == PartidaENUM.FINALIZADA) {
            throw new RuntimeException("esta partida finalizo");
        }

        partida.setPartidaENUM(PartidaENUM.FINALIZADA);
        return partidaRepository.save(partida);
    }

    public List<BeanPartida> obtenerHistorialPartidas(Long jugadorId) {
        return partidaRepository.findByJugadorId(jugadorId);
    }
}