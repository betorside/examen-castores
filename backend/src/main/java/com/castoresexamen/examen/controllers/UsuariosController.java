package com.castoresexamen.examen.controllers;

import com.castoresexamen.examen.models.UsuariosModel;
import com.castoresexamen.examen.services.UsuariosService;
import com.castoresexamen.examen.security.JwtUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String correo = body.get("correo");
        String contrasena = body.get("contrasena");
        UsuariosModel usuario = usuariosService.login(correo, contrasena);
        if (usuario != null) {
            String token = jwtUtil.generateToken(
                usuario.getCorreo(),
                usuario.getIdRol().getNombre(),
                usuario.getIdUsuario()
            );
            String rol = usuario.getIdRol().getNombre();
            return ResponseEntity.ok(Map.of("token", token, "rol", rol));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}