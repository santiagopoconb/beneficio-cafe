package umg.programacion.Beneficio_Cafe.agricultor.transporte;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogoTransporteReposity extends JpaRepository<CatalogoTransporte, Long> {
    Optional<CatalogoTransporte> findByPlaca(String placa);
}
