package umg.programacion.Beneficio_Cafe.agricultor.transportista;
import jakarta.validation.constraints.*;

public record DTOCrearTransportista(
        @NotBlank
        String nitTransportista,
        @NotBlank
        String nombreTransportista,
        @NotBlank
        String usuarioCreacion,
        @NotBlank
        String nitAgricultor
) {
}
