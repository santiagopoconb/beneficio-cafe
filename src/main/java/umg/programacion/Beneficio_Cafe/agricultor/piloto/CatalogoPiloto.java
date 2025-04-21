package umg.programacion.Beneficio_Cafe.agricultor.piloto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTransportista;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.EstadoTransportista;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name="catalogo_piloto", schema = "agricultor")
@Entity(name = "CatalogoPiloto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoPiloto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPiloto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transportista")
    private CatalogoTransportista idTransportista;
    @Column(name = "cui", unique = true)
    private String cui;
    private String nombre;
    private Date fechaNacimiento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_licencia")
    private TipoLicencia tipoLicencia;
    private Date fechaVencimientoLicencia;
    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;
    private boolean disponible;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "estado")
    private EstadoTransportista estadoPiloto;
    private String observaciones;

    public CatalogoPiloto (DTOCrearPiloto dto){
        this.cui = dto.cui();
        this.nombre = dto.nombre();
        this.fechaNacimiento = dto.fechaNacimiento();
        this.fechaVencimientoLicencia = dto.fechaVencimiento();
        this.usuarioCreacion = dto.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
        this.disponible = true;
        this.observaciones = dto.observaciones();
        EstadoTransportista estadoActivo = new EstadoTransportista();
        estadoActivo.setIdEstadoTransportista(1L);
        this.estadoPiloto = estadoActivo;
    }
}
