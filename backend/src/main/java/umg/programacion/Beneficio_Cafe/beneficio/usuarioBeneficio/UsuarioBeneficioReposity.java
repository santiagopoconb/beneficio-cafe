package umg.programacion.Beneficio_Cafe.beneficio.usuarioBeneficio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioBeneficioReposity extends JpaRepository<UsuarioBeneficio, Long> {
    Optional<UsuarioBeneficio> findByUsuario(String usuario);
}
