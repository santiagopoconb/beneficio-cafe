package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.DTOUsuario;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.Usuario;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.DTOJwtRespuesta;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.DTOJwtToken;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.TokenService;
import umg.programacion.Beneficio_Cafe.beneficio.usuarioBeneficio.UsuarioBeneficio;
import umg.programacion.Beneficio_Cafe.pesaje.usuarioPesaje.UsuarioPesaje;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class AutenticacionAgricultor {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<DTOJwtRespuesta> autenticarUsuario(@Valid @RequestBody DTOUsuario dtoUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoUsuario.usuario(),
                dtoUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var userDetails = usuarioAutenticado.getPrincipal();
        String nitAgricultor = null;
        String nombreRol;
        String usuario;

        if(userDetails instanceof Usuario usuarioAgricultor){
            nitAgricultor = usuarioAgricultor.getNitAgricultor();
            nombreRol = usuarioAgricultor.getIdRol().getRol();
            usuario = usuarioAgricultor.getUsuario();
        } else if(userDetails instanceof UsuarioPesaje usuarioPesaje){
            nombreRol = usuarioPesaje.getIdRol().getRol();
            usuario = usuarioPesaje.getUsuario();
        } else if(userDetails instanceof UsuarioBeneficio usuarioBeneficio) {
            nombreRol = usuarioBeneficio.getIdRol().getRol();
            usuario = usuarioBeneficio.getUsuario();
        } else{
            throw new RuntimeException("Usuario no encontrado");
        }

        String jwtToken = tokenService.generarToken((UserDetails) userDetails);
        return ResponseEntity.ok(new DTOJwtRespuesta(
                jwtToken,
                usuario,
                nombreRol,
                nitAgricultor));
    }
}
