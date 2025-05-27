package umg.programacion.Beneficio_Cafe.beneficio.pilotoBenefio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotoBeneficioReposity extends JpaRepository<PilotoBeneficio, Long> {
    PilotoBeneficio findByCui(String cui);
}
