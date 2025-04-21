package umg.programacion.Beneficio_Cafe.agricultor.transporte;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoPlacaReposity extends JpaRepository<TipoPlaca, Integer> {
    Optional<TipoPlaca> findByTipoPlaca(String tipoPlaca);
}
