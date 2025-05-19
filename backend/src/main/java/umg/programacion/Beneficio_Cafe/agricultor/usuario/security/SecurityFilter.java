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
import umg.programacion.Beneficio_Cafe.agricultor.usuario.UsuarioReposity;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UsuarioReposity usuarioReposity;
    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var subjet = tokenService.getSubject(token);
            if (subjet != null) {
                var usuario = usuarioReposity.findByUsuario(subjet);
                var athentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(athentication);
                //System.out.println("Usuario: " + usuario);
            }
        }
        filterChain.doFilter(request, response);
    }
}
