package umg.programacion.Beneficio_Cafe.pesaje.usuarioPesaje;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioPesajeReposity extends JpaRepository<UsuarioPesaje, Long> {
    Optional<UsuarioPesaje> findByUsuario(String usuario);
}
