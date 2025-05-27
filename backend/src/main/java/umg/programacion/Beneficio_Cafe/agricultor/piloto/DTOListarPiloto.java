package umg.programacion.Beneficio_Cafe.agricultor.piloto;

import java.time.LocalDate;
import java.util.Date;

public record DTOListarPiloto(
        String nitTransportista,
        String cui,
        String nombre,
        LocalDate fechaNacimiento,
        String tipoLicencia,
        LocalDate fechaVencimientoLicencia,
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
