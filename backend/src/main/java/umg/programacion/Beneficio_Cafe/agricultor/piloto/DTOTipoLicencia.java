package umg.programacion.Beneficio_Cafe.agricultor.piloto;

public record DTOTipoLicencia(
        Long idTipoLicencia,
        String tipoLicencia
) {
    public DTOTipoLicencia (TipoLicencia tipoLicencia){
      this(
              tipoLicencia.getIdTipo(),
              tipoLicencia.getTipoLicencia()
      );
    }
}
