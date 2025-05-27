package umg.programacion.Beneficio_Cafe.agricultor.controller.beneficio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import umg.programacion.Beneficio_Cafe.agricultor.transporte.CatalogoTransporteReposity;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.DTOActualizarCatologoTransportista;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.DTOCrearTransportista;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.TokenService;
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
     @Autowired
     private RestTemplate restTemplate;
     @Autowired
     private TokenService tokenService;

     private final String replicUrl =  "http://localhost:8081/transportista/replica";

     @PostMapping
     public ResponseEntity<?> replicarTansportista(@RequestBody @Valid DTOReplicaTransportista dtoReplicaTransportista){
          TransportistaBeneficio transportista = new TransportistaBeneficio(dtoReplicaTransportista);
          transportistaBeneficioReposity.save(transportista);
          return ResponseEntity.ok(Map.of("mensaje", "replica de transportista correctamente"));
     }

     @GetMapping
     public ResponseEntity<Page<DTOListarTransportisaBeneficio>> listarTransportistaBeneficio(@PageableDefault(size =  10)Pageable paginacion){
         return ResponseEntity.ok(transportistaBeneficioReposity.findAll(paginacion).map(DTOListarTransportisaBeneficio::new));
     }

     @PutMapping
     @Transactional
     public ResponseEntity actualizarTransportista(@RequestBody @Valid DTOActualizarTransportistaBeneficio actualizarTransportistaBeneficio) {
          TransportistaBeneficio transportistaBeneficio = transportistaBeneficioReposity.findByNitTransportista(actualizarTransportistaBeneficio
                  .nitTransportista());
          transportistaBeneficio.actualizarEstado(actualizarTransportistaBeneficio);

          DTOActualizarCatologoTransportista dtoAgricultor = new DTOActualizarCatologoTransportista(
                  actualizarTransportistaBeneficio.nitTransportista(),
                  actualizarTransportistaBeneficio.estadoTransportista(),
                  actualizarTransportistaBeneficio.usuarioCreacion());

          String jwtToken = tokenService.generarToken(
                  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
          );

          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_JSON);
          headers.set("Authorization", "Bearer " + jwtToken);

          HttpEntity<DTOActualizarCatologoTransportista> request = new HttpEntity<>(dtoAgricultor, headers);
          restTemplate.put(replicUrl, request);

          return ResponseEntity.ok(Map.of("mensaje", "Transportista actualizado"));
     }

     @GetMapping("/estado")
     public ResponseEntity<List<DTOEstadoTransportistaBenenficio>> estadoTransportista(){
          return ResponseEntity.ok(estadoTransportistaBeneficioReposity.findAll()
                  .stream()
                  .map(DTOEstadoTransportistaBenenficio::new).toList());
     }



}
