package umg.programacion.Beneficio_Cafe.agricultor.pesaje;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="pesaje", schema = "agricultor")
@Entity(name = "Pesaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pesaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPesaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medida")
    private MedidasPeso idMedida;
    private Integer pesoTotal;
    private String estado;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;

    public Pesaje(DTOCrearPesaje dtoCrearPesaje){
        this.pesoTotal = dtoCrearPesaje.pesoTotal();
        this.estado = dtoCrearPesaje.estado();
        this.fechaCreacion = LocalDateTime.now();
        this.usuarioCreacion = dtoCrearPesaje.usuarioCreacion();
    }
}
