package umg.programacion.Beneficio_Cafe.beneficio.pilotoBenefio;

import java.time.LocalDate;
import java.util.Date;

public record DTOReplicaPilotoBeneficio(
        String nitTransportista,
        String cui,
        String nombre,
        LocalDate fechaNacimiento,
        LocalDate FechaVencimientoLicencia,
        String tipoLicencia,
        String usuarioCreacion,
        String observaciones
) {
}
