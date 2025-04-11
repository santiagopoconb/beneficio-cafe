package umg.programacion.Beneficio_Cafe.agricultor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="catalogo_transportista", schema = "agricultor")
@Entity(name = "CatalogoTransportista")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoTransportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTransportista;
    private String nitTransportista;
    private String nombreTransportista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado")
    private EstadoTransportista estadoTransportista;

    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;
}
