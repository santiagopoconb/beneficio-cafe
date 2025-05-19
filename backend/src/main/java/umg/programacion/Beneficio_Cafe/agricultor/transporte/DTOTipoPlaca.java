package umg.programacion.Beneficio_Cafe.agricultor.transporte;

public record DTOTipoPlaca(
        Long idTipoPlaca,
        String tipoPlaca
) {

    public DTOTipoPlaca (TipoPlaca tipoPlaca){
        this(
                tipoPlaca.getIdTipo(),
                tipoPlaca.getTipoPlaca()
        );
    }
}
