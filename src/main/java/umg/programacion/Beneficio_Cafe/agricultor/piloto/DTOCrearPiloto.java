package umg.programacion.Beneficio_Cafe.agricultor.piloto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DTOCrearPiloto(
        @NotBlank
        String nitTransportista,
        @NotBlank
        String cui,
        @NotBlank
        String nombre,
        @NotNull
        Date fechaNacimiento,
        @NotBlank
        String tipoLicencia,
        @NotNull
        Date fechaVencimiento,
        @NotBlank
        String usuarioCreacion,
        String observaciones
) {
}
