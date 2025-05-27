package umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio;

public record DTOListarTransporteBeneficio(
        String nitAgricultor,
        String placa,
        String estado,
        String observaciones
) {

    public DTOListarTransporteBeneficio(TransporteBeneficio transporteBeneficio){
        this(
                transporteBeneficio.getIdTransportista().getNitAgricultor(),
                transporteBeneficio.getPlaca(),
                transporteBeneficio.getIdEstado().getEstadoTransportista(),
                transporteBeneficio.getObservaciones()
        );
    }
}
