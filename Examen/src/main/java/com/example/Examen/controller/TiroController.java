package com.example.Examen.controller;

import com.example.Examen.model.tiro.BeanTiro;
import com.example.Examen.service.tiro.TiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tiros")
public class TiroController {

    @Autowired
    private TiroService tiroService;


    @PostMapping("/realizar/{partidaId}")
    public ResponseEntity<BeanTiro> realizarTiro(@PathVariable Long partidaId) {
        return ResponseEntity.ok(tiroService.realizarTiro(partidaId));
    }
}