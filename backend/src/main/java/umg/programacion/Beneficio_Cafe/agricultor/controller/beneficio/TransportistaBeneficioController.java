package umg.programacion.Beneficio_Cafe.agricultor.controller.beneficio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import umg.programacion.Beneficio_Cafe.agricultor.transporte.CatalogoTransporteReposity;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/beneficio/transportista")
public class TransportistaBeneficioController {
     @Autowired
     private TransportistaBeneficioReposity transportistaBeneficioReposity;
     @Autowired
     private EstadoTransportistaBeneficioReposity estadoTransportistaBeneficioReposity;

     @GetMapping
     public ResponseEntity<Page<DTOListarTransportisaBeneficio>> listarTransportistaBeneficio(@PageableDefault(size =  10)Pageable paginacion){
         return ResponseEntity.ok(transportistaBeneficioReposity.findAll(paginacion).map(DTOListarTransportisaBeneficio::new));
     }

     @PutMapping
     @Transactional
     public ResponseEntity actualizarTransportista(@RequestBody @Valid DTOActualizarTransportistaBeneficio actualizarTransportistaBeneficio) {
          TransportistaBeneficio transportistaBeneficio = transportistaBeneficioReposity.findByNitTransportista(actualizarTransportistaBeneficio.nitTransportista());
          transportistaBeneficio.actualizarEstado(actualizarTransportistaBeneficio);
          return ResponseEntity.ok(Map.of("mensaje", "Transportista actualizado"));
     }

     @GetMapping("/estado")
     public ResponseEntity<List<DTOEstadoTransportistaBenenficio>> estadoTransportista(){
          return ResponseEntity.ok(estadoTransportistaBeneficioReposity.findAll()
                  .stream()
                  .map(DTOEstadoTransportistaBenenficio::new).toList());
     }



}
