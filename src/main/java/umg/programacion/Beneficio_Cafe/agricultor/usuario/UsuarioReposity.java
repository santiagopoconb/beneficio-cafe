package umg.programacion.Beneficio_Cafe.agricultor.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioReposity  extends JpaRepository<Usuario, Long> {
    UserDetails findByUsuario(String username);
}
