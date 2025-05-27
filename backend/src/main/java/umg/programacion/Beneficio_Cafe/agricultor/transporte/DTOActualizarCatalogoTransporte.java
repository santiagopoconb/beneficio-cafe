package umg.programacion.Beneficio_Cafe.agricultor.transporte;

import jakarta.validation.constraints.NotNull;

public record DTOActualizarCatalogoTransporte(
        @NotNull
        String placa,
        @NotNull
        Long estadoTransportista,

        String observaciones,
        @NotNull
        String usuarioCreacion
) {
}
