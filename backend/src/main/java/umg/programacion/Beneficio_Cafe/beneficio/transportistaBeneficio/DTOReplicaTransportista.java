package umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio;

import jakarta.validation.constraints.NotNull;

public record DTOReplicaTransportista(
        @NotNull
        String nitAgricultor,
        @NotNull
        String nitTransportista,
        @NotNull
        String nombreTranportista,
        @NotNull
        String usuarioCreacion
) {
}
