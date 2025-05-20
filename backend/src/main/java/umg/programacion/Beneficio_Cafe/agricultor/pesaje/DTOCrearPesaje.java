package umg.programacion.Beneficio_Cafe.agricultor.pesaje;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOCrearPesaje(
        @NotNull
        Integer idMedidaPeso,
        @NotNull
        Integer pesoTotal,
        @NotBlank
        String estado,
        @NotBlank
        String usuarioCreacion
) {
}
