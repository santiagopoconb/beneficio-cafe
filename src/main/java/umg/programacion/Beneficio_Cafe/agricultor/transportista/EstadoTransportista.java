package umg.programacion.Beneficio_Cafe.agricultor.transportista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="estado_transportista", schema = "agricultor")
@Entity(name = "EstadoTransportista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTransportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEstadoTransportista;
    private String estadoTransportista;
}
