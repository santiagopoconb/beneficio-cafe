package umg.programacion.Beneficio_Cafe.beneficio.pilotoBenefio;

public record DTOListarPilotoBeneficio(
        String nitAgricultor,
        String cui,
        String nombre,
        String estado,
        String observaciones
) {
    public DTOListarPilotoBeneficio(PilotoBeneficio pilotoBeneficio){
        this(
          pilotoBeneficio.getIdTransportista().getNitAgricultor(),
          pilotoBeneficio.getCui(),
          pilotoBeneficio.getNombre(),
          pilotoBeneficio.getIdEstado().getEstadoTransportista(),
          pilotoBeneficio.getObservaciones()
        );
    }
}
