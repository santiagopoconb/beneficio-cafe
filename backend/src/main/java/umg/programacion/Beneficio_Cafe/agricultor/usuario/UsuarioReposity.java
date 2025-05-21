package umg.programacion.Beneficio_Cafe.agricultor.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioReposity  extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String username);
}
