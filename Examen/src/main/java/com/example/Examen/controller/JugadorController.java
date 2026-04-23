package com.example.Examen.controller;

import com.example.Examen.dto.RecargarSaldoDTO;
import com.example.Examen.model.jugador.BeanJugador;
import com.example.Examen.service.jugador.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;


    @PutMapping("/{id}/recargar")
    public ResponseEntity<BeanJugador> recargarSaldo(
            @PathVariable Long id,
            @RequestBody RecargarSaldoDTO dto) {
        return ResponseEntity.ok(jugadorService.recargarSaldo(id, dto.getMonto()));
    }
}