package umg.programacion.Beneficio_Cafe.agricultor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="tipo_placa", schema = "agricultor")
@Entity(name = "TipoPlaca")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoPlaca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipo;
    private String tipoPlaca;
}
