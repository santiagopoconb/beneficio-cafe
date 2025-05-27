package umg.programacion.Beneficio_Cafe.agricultor.transportista;

import jakarta.validation.constraints.NotNull;

public record DTOActualizarCatologoTransportista(
        @NotNull
        String nitTransportista,
        @NotNull
        Long estadoTransportista,
        @NotNull
        String usuarioCreacion
) {
}