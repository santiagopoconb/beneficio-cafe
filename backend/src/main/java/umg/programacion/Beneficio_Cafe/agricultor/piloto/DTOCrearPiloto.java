package umg.programacion.Beneficio_Cafe.agricultor.piloto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record DTOCrearPiloto(
        @NotBlank
        String nitTransportista,
        @NotBlank
        String cui,
        @NotBlank
        String nombre,
        @NotNull
        LocalDate fechaNacimiento,
        @NotBlank
        String tipoLicencia,
        @NotNull
        LocalDate fechaVencimiento,
        @NotBlank
        String usuarioCreacion,
        String observaciones
) {
}
