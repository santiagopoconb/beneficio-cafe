package umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.EstadoTransportistaBeneficio;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.TransportistaBeneficio;

import java.time.LocalDateTime;

@Table(name="catalogo_transporte", schema = "beneficio")
@Entity(name = "CatalogoTransporteBeneficio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransporteBeneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTransporte;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transportista")
    private TransportistaBeneficio idTransportista;
    private String tipoPlaca;
    private String placa;
    private String marca;
    private String color;
    private String linea;
    private Integer modelo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado")
    private EstadoTransportistaBeneficio idEstado;
    private boolean disponible;
    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;
    private String observaciones;

    public TransporteBeneficio(DTOReplicaTransporteBeneficio dto, TransportistaBeneficio transportistaBeneficio) {

        this.idTransportista = transportistaBeneficio;
        this.tipoPlaca = dto.tipoPlaca();
        this.placa = dto.placa();
        this.marca = dto.marca();
        this.color = dto.color();
        this.linea = dto.linea();
        this.modelo = dto.modelo();

        EstadoTransportistaBeneficio estado = new EstadoTransportistaBeneficio();
        estado.setIdEstadoTransportista(1L);
        this.idEstado = estado;

        this.disponible = true;
        this.usuarioCreacion = dto.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
        this.observaciones = dto.observaciones();
    }

    public void actualizarTransporteBeneficio(DTOActualizarTransporteBeneficio dto){
        EstadoTransportistaBeneficio estado = new EstadoTransportistaBeneficio();
        estado.setIdEstadoTransportista(dto.idEstado());
        this.idEstado = estado;
        this.observaciones = dto.observaciones();
        this.usuarioCreacion = dto.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
    }

}
