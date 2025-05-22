package umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportistaBeneficioReposity extends JpaRepository<TransportistaBeneficio, Long> {
    TransportistaBeneficio findByNitTransportista(String nitTransportista);
}
