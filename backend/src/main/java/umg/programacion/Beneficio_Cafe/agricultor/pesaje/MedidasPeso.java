package umg.programacion.Beneficio_Cafe.agricultor.pesaje;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="medidas_peso", schema = "agricultor")
@Entity(name = "MedidasPeso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedidasPeso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMedida;
    private String medida;
}
