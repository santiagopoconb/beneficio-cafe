package umg.programacion.Beneficio_Cafe.agricultor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="tipo_licencia", schema = "agricultor")
@Entity(name = "TipoLicencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoLicencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipo;
    private String tipoLicencia;
}