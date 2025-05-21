package umg.programacion.Beneficio_Cafe.beneficio.usuarioBeneficio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="rol", schema = "beneficio")
@Entity(name = "RolBeneficio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolBeneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRol;
    private String rol;
    private String actor;
}
