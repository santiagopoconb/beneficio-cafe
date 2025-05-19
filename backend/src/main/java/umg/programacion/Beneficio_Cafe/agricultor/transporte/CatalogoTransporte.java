package umg.programacion.Beneficio_Cafe.agricultor.transporte;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTransportista;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.EstadoTransportista;

import java.time.LocalDateTime;

@Table(name="catalogo_transporte", schema = "agricultor")
@Entity(name = "CatalogoTransporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTransporte;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transportista")
    private CatalogoTransportista idTransportista;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_placa")
    private TipoPlaca idPlaca;
    @Column(name = "placa", unique = true)
    private String placa;
    private String marca;
    private String color;
    private String linea;
    private Integer modelo;
    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;
    boolean disponible;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado")
    private EstadoTransportista estadoTransporte;
    private String observaciones;

    public CatalogoTransporte(DTOCrearTransporte dto){
        this.placa = dto.placa();
        this.marca = dto.marca();
        this.color = dto.color();
        this.linea = dto.linea();
        this.modelo = dto.modelo();
        this.usuarioCreacion = dto.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
        this.disponible = true;
        this.observaciones = dto.observaciones();
        EstadoTransportista estadoActivo = new EstadoTransportista();
        estadoActivo.setIdEstadoTransportista(1L);
        this.estadoTransporte = estadoActivo;
    }
}
