package umg.programacion.Beneficio_Cafe.beneficio.pilotoBenefio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.EstadoTransportistaBeneficio;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.TransportistaBeneficio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name="piloto", schema = "beneficio")
@Entity(name = "PilotoBeneficio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PilotoBeneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPiloto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transportista")
    private TransportistaBeneficio idTransportista;
    private String cui;
    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate fechaVencimientoLicencia;
    private String tipoLicencia;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado")
    private EstadoTransportistaBeneficio idEstado;
    private boolean disponible;
    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;
    private String observaciones;

    public PilotoBeneficio(DTOReplicaPilotoBeneficio dto, TransportistaBeneficio transportistaBeneficio) {
        this.idTransportista = transportistaBeneficio;
        this.cui = dto.cui();
        this.nombre = dto.nombre();
        this.fechaNacimiento = dto.fechaNacimiento();
        this.fechaVencimientoLicencia = dto.FechaVencimientoLicencia();
        this.tipoLicencia = dto.tipoLicencia();

        EstadoTransportistaBeneficio estado = new EstadoTransportistaBeneficio();
        estado.setIdEstadoTransportista(1L);

        this.idEstado = estado;
        this.disponible = true;
        this.usuarioCreacion = dto.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
        this.observaciones = dto.observaciones();
    }

    public void actualizarPilotoBeneficio(DTOActualizarPilotoBeneficio dto) {
        EstadoTransportistaBeneficio estado = new EstadoTransportistaBeneficio();
        estado.setIdEstadoTransportista(dto.idEstado());
        this.idEstado = estado;
        this.observaciones = dto.observaciones();
        this.usuarioCreacion = dto.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
    }
}
