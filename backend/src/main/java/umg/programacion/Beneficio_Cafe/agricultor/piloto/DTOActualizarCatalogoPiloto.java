package umg.programacion.Beneficio_Cafe.agricultor.piloto;

import jakarta.validation.constraints.NotNull;

public record DTOActualizarCatalogoPiloto(
        @NotNull
        String cui,
        @NotNull
        Long estado,

        String observaciones,
        @NotNull
        String usuarioCreacion
) {
}
