package umg.programacion.Beneficio_Cafe.agricultor.transportista;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogoTranportistaReposity extends JpaRepository<CatalogoTransportista, Long> {
    Optional<CatalogoTransportista> findByNitTransportista(String nit);
}
