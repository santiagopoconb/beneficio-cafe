package umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransporteBeneficioReposity  extends JpaRepository<TransporteBeneficio, Long> {
    TransporteBeneficio findByPlaca(String placa);

    Page<TransporteBeneficio> findByIdEstado_IdEstadoTransportista(Long idEstado, Pageable pageable);

    Page<TransporteBeneficio> findByPlacaContainingIgnoreCaseAndIdEstado_IdEstadoTransportista(String placa, Long estado, Pageable pageable);

    Page<TransporteBeneficio> findByPlacaContainingIgnoreCase(String placa, Pageable paginacion);
}
