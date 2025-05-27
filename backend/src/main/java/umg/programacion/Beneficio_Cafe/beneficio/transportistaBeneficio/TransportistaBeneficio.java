package umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.EstadoTransportista;

import java.time.LocalDateTime;

@Table(name="catalogo_transportista", schema = "beneficio")
@Entity(name = "TransportistaBeneficio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportistaBeneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransportista;
    private String nitAgricultor;
    private String nitTransportista;
    private String nombreTransportista;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado")
    private EstadoTransportistaBeneficio estadoTransportista;
    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;

    public void actualizarEstado(DTOActualizarTransportistaBeneficio u) {
        if(u.estadoTransportista()!=null) {
            EstadoTransportistaBeneficio estado = new EstadoTransportistaBeneficio();
            estado.setIdEstadoTransportista(u.estadoTransportista());
            this.estadoTransportista = estado;
            this.usuarioCreacion = u.usuarioCreacion();
            this.fechaCreacion = LocalDateTime.now();
        }
    }

    public TransportistaBeneficio (DTOReplicaTransportista replicaTransportista) {
        this.nitAgricultor = replicaTransportista.nitAgricultor();
        this.nitTransportista = replicaTransportista.nitTransportista();
        this.nombreTransportista = replicaTransportista.nombreTranportista();

        EstadoTransportistaBeneficio estadoActivo = new EstadoTransportistaBeneficio();
        estadoActivo.setIdEstadoTransportista(1L);
        this.estadoTransportista = estadoActivo;

        this.usuarioCreacion = replicaTransportista.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
    }
}
