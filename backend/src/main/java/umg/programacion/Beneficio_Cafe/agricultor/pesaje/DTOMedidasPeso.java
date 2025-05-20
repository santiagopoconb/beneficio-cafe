package umg.programacion.Beneficio_Cafe.agricultor.pesaje;

public record DTOMedidasPeso(
        Long idMedidaPeso,
        String medida
) {
    public DTOMedidasPeso(MedidasPeso medidaPeso) {
        this(
                medidaPeso.getIdMedida(),
                medidaPeso.getMedida()
        );
    }
}
