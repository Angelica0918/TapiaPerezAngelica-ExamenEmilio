package com.example.Examen.service.tiro;

import com.example.Examen.model.jugador.BeanJugador;
import com.example.Examen.model.jugador.JugadorRepository;
import com.example.Examen.model.partida.BeanPartida;
import com.example.Examen.model.partida.PartidaENUM;
import com.example.Examen.model.partida.PartidaRepository;
import com.example.Examen.model.tiro.BeanTiro;
import com.example.Examen.model.tiro.TiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TiroService {
    @Autowired
    private TiroRepository tiroRepository;
    @Autowired
    private PartidaRepository partidaRepository;
    @Autowired
    private JugadorRepository jugadorRepository;

    public BeanTiro realizarTiro(Long partidaId) {

        BeanPartida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("no se encontro la partida"));

        if (partida.getPartidaENUM() == PartidaENUM.FINALIZADA) {
            throw new RuntimeException("esta partida finalizo");
        }

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

            esGanador = false;
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
}