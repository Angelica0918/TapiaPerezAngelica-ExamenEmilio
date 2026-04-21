package com.example.Examen.controller;

import com.example.Examen.dto.IniciarPartidaDTO;
import com.example.Examen.model.partida.BeanPartida;
import com.example.Examen.service.partida.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @PostMapping("/iniciar")
    public ResponseEntity<BeanPartida> iniciarPartida(@RequestBody IniciarPartidaDTO dto) {
        return ResponseEntity.ok(partidaService.iniciarPartida(dto.getJugadorId(), dto.getApuesta()));
    }


    @PutMapping("/{id}/finalizar")
    public ResponseEntity<BeanPartida> finalizarPartida(@PathVariable Long id) {
        return ResponseEntity.ok(partidaService.finalizarPartidaManual(id));
    }


    @GetMapping("/historial/{jugadorId}")
    public ResponseEntity<List<BeanPartida>> historial(@PathVariable Long jugadorId) {
        return ResponseEntity.ok(partidaService.obtenerHistorialPartidas(jugadorId));
    }
}