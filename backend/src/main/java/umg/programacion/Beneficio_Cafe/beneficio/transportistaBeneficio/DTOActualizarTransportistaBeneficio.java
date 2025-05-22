package umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio;

import jakarta.validation.constraints.NotNull;

public record DTOActualizarTransportistaBeneficio(
        @NotNull
        String nitTransportista,
        @NotNull
        Long estadoTransportista){
}
