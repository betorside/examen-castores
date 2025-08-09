package com.castoresexamen.examen.controllers;

import com.castoresexamen.examen.models.ProductosModel;
import com.castoresexamen.examen.services.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @GetMapping
    public List<ProductosModel> getInventario() {
        return productosService.getInventario();
    }

    @GetMapping("/activos")
    public List<ProductosModel> getActivos() {
        return productosService.getByEstatus(1);
    }

    @GetMapping("/inactivos")
    public List<ProductosModel> getInactivos() {
        return productosService.getByEstatus(0);
    }

    @PostMapping
    public ProductosModel agregarProducto(@RequestBody ProductosModel producto) {
        return productosService.agregarProducto(producto);
    }

    @PutMapping("/entrada/{id}")
    public ResponseEntity<?> entradaProducto(@PathVariable Long id, @RequestBody Map<String, Integer> body, HttpServletRequest request) {
        Long idUsuario = (Long) request.getAttribute("idUsuario");
        int cantidad = body.get("cantidad");
        try {
            ProductosModel prod = productosService.entradaProducto(id, cantidad, idUsuario);
            return ResponseEntity.ok(prod);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/salida/{id}")
    public ResponseEntity<?> salidaProducto(@PathVariable Long id, @RequestBody Map<String, Integer> body, HttpServletRequest request) {
        Long idUsuario = (Long) request.getAttribute("idUsuario");
        int cantidad = body.get("cantidad");
        try {
            ProductosModel prod = productosService.salidaProducto(id, cantidad, idUsuario);
            return ResponseEntity.ok(prod);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/baja/{id}")
    public ProductosModel bajaProducto(@PathVariable Long id) throws Exception {
        return productosService.bajaProducto(id);
    }

    @PutMapping("/activar/{id}")
    public ProductosModel activarProducto(@PathVariable Long id) throws Exception {
        return productosService.activarProducto(id);
    }
}