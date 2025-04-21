package umg.programacion.Beneficio_Cafe.agricultor.transporte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOCrearTransporte(
        @NotBlank
        String nitTransportista,
        @NotBlank
        String tipoPlaca,
        @NotBlank
        String placa,
        @NotBlank
        String marca,
        @NotBlank
        String color,
        @NotBlank
        String linea,
        @NotNull
        Integer modelo,
        @NotBlank
        String usuarioCreacion,
        String observaciones

) {
}
