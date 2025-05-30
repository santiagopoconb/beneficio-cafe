package umg.programacion.Beneficio_Cafe.agricultor.usuario.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.Usuario;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.UsuarioReposity;
import umg.programacion.Beneficio_Cafe.beneficio.usuarioBeneficio.UsuarioBeneficioReposity;
import umg.programacion.Beneficio_Cafe.pesaje.usuarioPesaje.UsuarioPesaje;
import umg.programacion.Beneficio_Cafe.pesaje.usuarioPesaje.UsuarioPesajeReposity;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UsuarioReposity usuarioReposity;
    @Autowired
    private UsuarioBeneficioReposity usuarioBeneficioReposity;
    @Autowired
    private UsuarioPesajeReposity usuarioPesajeReposity;
    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var subjet = tokenService.getSubject(token);
            if (subjet != null) {
                Optional<Usuario> usuarioAgricultor =usuarioReposity.findByUsuario(subjet);
                if(usuarioAgricultor.isPresent()) {
                    var usuario = usuarioAgricultor.get();
                    var athentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(athentication);
                    //System.out.println("Usuario: " + usuario);
                } else {
                    Optional<UsuarioPesaje> usuarioPesaje = usuarioPesajeReposity.findByUsuario(subjet);
                    if(usuarioPesaje.isPresent()) {
                        var usuario = usuarioPesaje.get();
                        var athentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(athentication);
                    } else {
                        var usuarioBeneficio = usuarioBeneficioReposity.findByUsuario(subjet)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                        var athentication = new UsernamePasswordAuthenticationToken(usuarioBeneficio, null, usuarioBeneficio.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(athentication);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
