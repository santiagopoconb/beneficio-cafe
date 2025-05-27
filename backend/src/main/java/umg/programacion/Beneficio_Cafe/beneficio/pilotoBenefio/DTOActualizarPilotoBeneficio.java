package umg.programacion.Beneficio_Cafe.beneficio.pilotoBenefio;

import jakarta.validation.constraints.NotNull;

public record DTOActualizarPilotoBeneficio(
        @NotNull
        String cui,
        @NotNull
        Long idEstado,

        String observaciones,
        @NotNull
        String usuarioCreacion
) {
}
