package umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio;

import jakarta.validation.constraints.NotNull;

public record DTOActualizarTransporteBeneficio(
        @NotNull
        String placa,
        @NotNull
        Long idEstado,

        String observaciones,
        @NotNull
        String usuarioCreacion
) {
}
