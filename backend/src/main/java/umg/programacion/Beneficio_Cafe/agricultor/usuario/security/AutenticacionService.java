package umg.programacion.Beneficio_Cafe.agricultor.usuario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.Usuario;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.UsuarioReposity;
import umg.programacion.Beneficio_Cafe.beneficio.usuarioBeneficio.UsuarioBeneficio;
import umg.programacion.Beneficio_Cafe.beneficio.usuarioBeneficio.UsuarioBeneficioReposity;

@Service
public class AutenticacionService  implements UserDetailsService {

    @Autowired
    private UsuarioReposity usuarioReposity;
    @Autowired
    private UsuarioBeneficioReposity usuarioBeneficioReposity;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return usuarioReposity.findByUsuario(username)
                .map(user ->(UserDetails) user)
                .or(()->usuarioBeneficioReposity.findByUsuario(username)
                        .map(user -> (UserDetails) user))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
