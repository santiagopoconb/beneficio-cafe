package umg.programacion.Beneficio_Cafe.agricultor.transporte;

public record DTOListarTransporte(
        String nitTransportista,
        String tipoPlaca,
        String placa,
        String marca,
        String color,
        String linea,
        Integer modelo,
        boolean disponible,
        String estado,
        String observaciones
) {
    public DTOListarTransporte (CatalogoTransporte catalogoTransporte){
        this(
                catalogoTransporte.getIdTransportista().getNitTransportista(),
                catalogoTransporte.getIdPlaca().getTipoPlaca(),
                catalogoTransporte.getPlaca(),
                catalogoTransporte.getMarca(),
                catalogoTransporte.getColor(),
                catalogoTransporte.getLinea(),
                catalogoTransporte.getModelo(),
                catalogoTransporte.isDisponible(),
                catalogoTransporte.getEstadoTransporte().getEstadoTransportista(),
                catalogoTransporte.getObservaciones()
        );
    }

}
