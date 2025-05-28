package umg.programacion.Beneficio_Cafe.beneficio.pilotoBenefio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotoBeneficioReposity extends JpaRepository<PilotoBeneficio, Long> {
    PilotoBeneficio findByCui(String cui);

    Page<PilotoBeneficio> findByCuiContainingIgnoreCase(String cui, Pageable paginacion);

    Page<PilotoBeneficio> findByIdEstado_IdEstadoTransportista(Long estado, Pageable paginacion);

    Page<PilotoBeneficio> findByCuiContainingIgnoreCaseAndIdEstado_IdEstadoTransportista(String cui, Long estado, Pageable paginacion);
}
