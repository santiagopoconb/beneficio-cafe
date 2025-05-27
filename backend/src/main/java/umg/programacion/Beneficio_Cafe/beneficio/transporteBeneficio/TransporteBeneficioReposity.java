package umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransporteBeneficioReposity  extends JpaRepository<TransporteBeneficio, Long> {
    TransporteBeneficio findByPlaca(String placa);
}
