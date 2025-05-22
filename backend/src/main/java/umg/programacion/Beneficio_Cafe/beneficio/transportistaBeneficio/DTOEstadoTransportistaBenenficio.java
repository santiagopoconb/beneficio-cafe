package umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio;

public record DTOEstadoTransportistaBenenficio(
        Long idEstadoTransportistaBeneficio,
        String estadoTransportistaBeneficio
) {
    public DTOEstadoTransportistaBenenficio(EstadoTransportistaBeneficio estadoTransportistaBeneficio){
        this(
                estadoTransportistaBeneficio.getIdEstadoTransportista(),
                estadoTransportistaBeneficio.getEstadoTransportista()
        );
    }
}
