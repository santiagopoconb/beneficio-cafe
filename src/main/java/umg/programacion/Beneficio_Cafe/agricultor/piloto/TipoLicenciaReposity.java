package umg.programacion.Beneficio_Cafe.agricultor.piloto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoLicenciaReposity extends JpaRepository<TipoLicencia, Long> {
    Optional<TipoLicencia> findByTipoLicencia(String tipoLicencia);
}
