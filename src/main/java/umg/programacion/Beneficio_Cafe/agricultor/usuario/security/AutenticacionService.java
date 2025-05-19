package umg.programacion.Beneficio_Cafe.agricultor.usuario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.UsuarioReposity;

@Service
public class AutenticacionService  implements UserDetailsService {

    @Autowired
    private UsuarioReposity usuarioReposity;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return usuarioReposity.findByUsuario(username);
    }
}
