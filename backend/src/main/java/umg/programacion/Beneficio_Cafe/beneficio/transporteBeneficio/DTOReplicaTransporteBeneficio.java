package umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio;

public record DTOReplicaTransporteBeneficio(
        String nitTransportista,
        String tipoPlaca,
        String placa,
        String marca,
        String color,
        String linea,
        Integer modelo,
        String usuarioCreacion,
        String observaciones
) {
}
