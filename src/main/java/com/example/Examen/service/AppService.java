package com.example.Examen.service;

import com.example.Examen.model.jugador.BeanJugador;
import com.example.Examen.model.jugador.JugadorRepository;
import com.example.Examen.model.partida.BeanPartida;
import com.example.Examen.model.partida.PartidaENUM;
import com.example.Examen.model.partida.PartidaRepository;
import com.example.Examen.model.tiro.BeanTiro;
import com.example.Examen.model.tiro.TiroRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class AppService {

    private JugadorRepository jugadorRepository;
    private PartidaRepository partidaRepository;
    private TiroRepository tiroRepository;

    public AppService(JugadorRepository jugadorRepository,
                      PartidaRepository partidaRepository,
                      TiroRepository tiroRepository) {
        this.jugadorRepository = jugadorRepository;
        this.partidaRepository = partidaRepository;
        this.tiroRepository = tiroRepository;
    }

    public BeanPartida iniciarPartida(Long jugadorId, double apuesta) {
        BeanJugador jugador = jugadorRepository.findById(jugadorId).get();

        if (!jugador.isActivo()) {
            throw new RuntimeException("El jugador no esta activo");
        }
        if (jugador.getSaldo() < apuesta) {
            throw new RuntimeException("Saldo insuficiente");
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

    public BeanTiro realizarTiro(Long partidaId) {
        BeanPartida partida = partidaRepository.findById(partidaId).get();

        Random random = new Random();
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int suma = dado1 + dado2;

        boolean esGanador = false;

        if (suma == 7 || suma == 11) {
            esGanador = true;
            partida.setPartidaENUM(PartidaENUM.FINALIZADA);
            BeanJugador jugador = partida.getJugador();
            jugador.setSaldo(jugador.getSaldo() + (partida.getApuesta() * 2));
            jugadorRepository.save(jugador);

        } else if (suma == 2 || suma == 3 || suma == 12) {
            partida.setPartidaENUM(PartidaENUM.FINALIZADA);
        }

        partidaRepository.save(partida);

        BeanTiro tiro = new BeanTiro();
        tiro.setPartida(partida);
        tiro.setValorDado1(dado1);
        tiro.setValorDado2(dado2);
        tiro.setSuma(suma);
        tiro.setEsGanador(esGanador);

        return tiroRepository.save(tiro);
    }

    public BeanPartida finalizarPartida(Long partidaId) {
        BeanPartida partida = partidaRepository.findById(partidaId).get();
        partida.setPartidaENUM(PartidaENUM.FINALIZADA);
        return partidaRepository.save(partida);
    }

    public BeanJugador recargarSaldo(Long jugadorId, double monto) {
        BeanJugador jugador = jugadorRepository.findById(jugadorId).get();
        jugador.setSaldo(jugador.getSaldo() + monto);
        return jugadorRepository.save(jugador);
    }

    public List<BeanPartida> obtenerHistorialPartidas(Long jugadorId) {
        return partidaRepository.findByJugadorId(jugadorId);
    }
}