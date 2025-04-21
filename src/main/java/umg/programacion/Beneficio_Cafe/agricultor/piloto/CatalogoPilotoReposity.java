package umg.programacion.Beneficio_Cafe.agricultor.piloto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogoPilotoReposity extends JpaRepository<CatalogoPiloto, Long> {
    Optional<CatalogoPiloto> findByCui(String cui);
}
