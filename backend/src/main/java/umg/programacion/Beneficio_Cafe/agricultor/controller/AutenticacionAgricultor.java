package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.DTOUsuario;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.Usuario;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.DTOJwtRespuesta;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.DTOJwtToken;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.TokenService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class AutenticacionAgricultor {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario(@Valid @RequestBody DTOUsuario dtoUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoUsuario.usuario(),
                dtoUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var usuario = (Usuario) usuarioAutenticado.getPrincipal();
        var jwtToken = tokenService.generarToken(usuario);
        return ResponseEntity.ok(new DTOJwtRespuesta(
                jwtToken,
                usuario.getUsuario(),
                (int) usuario.getIdRol().getIdRol()));
    }
}
