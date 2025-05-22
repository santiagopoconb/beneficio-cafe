package umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="estado_transportista", schema = "beneficio")
@Entity(name = "EstadoTransportistaBeneficio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTransportistaBeneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoTransportista;
    private String estadoTransportista;
}
