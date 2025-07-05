package com.hospital.hospitalcrud.controlador;

import com.hospital.hospitalcrud.dto.LoginRequest;
import com.hospital.hospitalcrud.dto.JwtResponse;
import com.hospital.hospitalcrud.entidad.Usuario;
import com.hospital.hospitalcrud.servicio.UsuarioService;
import com.hospital.hospitalcrud.seguridad.JWTService;
import com.hospital.hospitalcrud.servicio.DetallesUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DetallesUsuarioService userDetailsService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtService.generateToken(userDetails);

        return new JwtResponse(jwt);
    }

    @PostMapping("/registro")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }
}
