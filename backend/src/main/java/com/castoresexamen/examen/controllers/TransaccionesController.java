package com.castoresexamen.examen.controllers;

import com.castoresexamen.examen.models.TransaccionesModel;
import com.castoresexamen.examen.repositories.TransaccionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacciones")
public class TransaccionesController {

    @Autowired
    private TransaccionesRepository transaccionesRepository;

    // Histórico completo o filtrado por tipo
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getHistorico(@RequestParam(required = false) String tipo) {
        List<TransaccionesModel> transacciones;
        if (tipo != null && !tipo.isEmpty()) {
            transacciones = transaccionesRepository.findByTipoOrderByFechaTransaccionAsc(tipo);
        } else {
            transacciones = transaccionesRepository.findAllByOrderByFechaTransaccionAsc();
        }

        List<Map<String, Object>> responseList = transacciones.stream().map(trans -> {
            Map<String, Object> map = new HashMap<>();
            map.put("usuario", trans.getIdUsuario().getNombre());
            map.put("producto", trans.getIdProducto().getNombre());
            map.put("fecha", trans.getFechaTransaccion());
            map.put("tipo", trans.getTipo());
            map.put("cantidad", trans.getCantidad());
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}