package umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio;

public record DTOListarTransportisaBeneficio(
        String nitAgricultor,
        String nitTransportista,
        String nombreTransportista,
        String estadoTransportista
) {

    public DTOListarTransportisaBeneficio (TransportistaBeneficio transportistaBeneficio){
        this(
                transportistaBeneficio.getNitAgricultor(),
                transportistaBeneficio.getNitTransportista(),
                transportistaBeneficio.getNombreTransportista(),
                transportistaBeneficio.getEstadoTransportista().getEstadoTransportista());
    }
}
