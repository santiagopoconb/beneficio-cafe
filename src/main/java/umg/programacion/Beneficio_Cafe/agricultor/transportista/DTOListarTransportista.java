package umg.programacion.Beneficio_Cafe.agricultor.transportista;

import java.time.LocalDateTime;

public record DTOListarTransportista(String nitTransportista,
                                     String nombreTransportista,
                                     String estado) {

    public DTOListarTransportista (CatalogoTransportista catalogoTransportista){
        this(catalogoTransportista.getNitTransportista(),
                catalogoTransportista.getNombreTransportista(),
                catalogoTransportista.getEstadoTransportista().getEstadoTransportista());
    }
}
