package umg.programacion.Beneficio_Cafe.agricultor.transportista;

import java.time.LocalDateTime;

public record DTOListarTransportista(Long idTransportista,
                                     String nitTransportista,
                                     String nombreTransportista,
                                     Integer estado,
                                     String usuarioCreacion,
                                     LocalDateTime fechaCreacion) {

    public DTOListarTransportista (CatalogoTransportista catalogoTransportista){
        this(catalogoTransportista.getIdTransportista(),
                catalogoTransportista.getNitTransportista(),
                catalogoTransportista.getNombreTransportista(),
                (int) catalogoTransportista.getEstadoTransportista().getIdEstadoTransportista(),
                catalogoTransportista.getUsuarioCreacion(),
                catalogoTransportista.getFechaCreacion());
    }
}
