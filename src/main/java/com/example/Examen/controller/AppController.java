package com.example.Examen.controller;

import com.example.Examen.model.jugador.BeanJugador;
import com.example.Examen.model.partida.BeanPartida;
import com.example.Examen.model.tiro.BeanTiro;
import com.example.Examen.service.AppService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    private AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping("/partida/iniciar")
    public BeanPartida iniciarPartida(@RequestParam Long jugadorId,
                                      @RequestParam double apuesta) {
        return appService.iniciarPartida(jugadorId, apuesta);
    }

    @PostMapping("/tiro/realizar")
    public BeanTiro realizarTiro(@RequestParam Long partidaId) {
        return appService.realizarTiro(partidaId);
    }

    @PutMapping("/partida/finalizar")
    public BeanPartida finalizarPartida(@RequestParam Long partidaId) {
        return appService.finalizarPartida(partidaId);
    }

    @PutMapping("/jugador/recargar")
    public BeanJugador recargarSaldo(@RequestParam Long jugadorId,
                                     @RequestParam double monto) {
        return appService.recargarSaldo(jugadorId, monto);
    }

    @GetMapping("/partidas/historial")
    public List<BeanPartida> obtenerHistorial(@RequestParam Long jugadorId) {
        return appService.obtenerHistorialPartidas(jugadorId);
    }
}