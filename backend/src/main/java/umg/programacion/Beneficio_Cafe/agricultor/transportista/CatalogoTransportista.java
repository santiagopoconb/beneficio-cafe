package umg.programacion.Beneficio_Cafe.agricultor.transportista;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private EstadoTransportista estadoTransportista;

    private String usuarioCreacion;
    private LocalDateTime fechaCreacion;

    public CatalogoTransportista (DTOCrearTransportista nuevoRegistro){
        this.nitTransportista = nuevoRegistro.nitTransportista();
        this.nombreTransportista = nuevoRegistro.nombreTransportista();

        EstadoTransportista estadoActivo = new EstadoTransportista();
        estadoActivo.setIdEstadoTransportista(1L);
        this.estadoTransportista = estadoActivo;

        this.usuarioCreacion = nuevoRegistro.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizarCatalogoTransporista(DTOActualizarCatologoTransportista dto){
        if(dto.estadoTransportista()!=null){
            EstadoTransportista estado = new EstadoTransportista();
            estado.setIdEstadoTransportista(dto.estadoTransportista());
            this.estadoTransportista = estado;
        }
        this.usuarioCreacion = dto.usuarioCreacion();
        this.fechaCreacion = LocalDateTime.now();
    }
}
