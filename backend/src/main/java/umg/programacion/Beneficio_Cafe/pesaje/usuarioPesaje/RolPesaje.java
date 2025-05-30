package umg.programacion.Beneficio_Cafe.pesaje.usuarioPesaje;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="rol", schema = "pesaje")
@Entity(name = "RolPesaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolPesaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRol;
    private String rol;
    private String actor;
}
