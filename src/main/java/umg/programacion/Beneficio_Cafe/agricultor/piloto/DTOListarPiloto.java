package umg.programacion.Beneficio_Cafe.agricultor.piloto;

import java.util.Date;

public record DTOListarPiloto(
        String nitTransportista,
        String cui,
        String nombre,
        Date fechaNacimiento,
        String tipoLicencia,
        Date fechaVencimientoLicencia,
        boolean disponible,
        String estado,
        String observaciones
) {
    public DTOListarPiloto (CatalogoPiloto piloto){
        this(
                piloto.getIdTransportista().getNitTransportista(),
                piloto.getCui(),
                piloto.getNombre(),
                piloto.getFechaNacimiento(),
                piloto.getTipoLicencia().getTipoLicencia(),
                piloto.getFechaVencimientoLicencia(),
                piloto.isDisponible(),
                piloto.getEstadoPiloto().getEstadoTransportista(),
                piloto.getObservaciones()
        );
    }

}
