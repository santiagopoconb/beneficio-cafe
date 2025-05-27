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
import umg.programacion.Beneficio_Cafe.agricultor.transporte.DTOActualizarCatalogoTransporte;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.TokenService;
import umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio.*;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.TransportistaBeneficio;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.TransportistaBeneficioReposity;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/beneficio/transporte")
public class TransporteBeneficioController {
    @Autowired
    private TransporteBeneficioReposity transporteBeneficioReposity;
    @Autowired
    private TransportistaBeneficioReposity transportistaBeneficioReposity;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TokenService tokenService;

    private final String replicUrl =  "http://localhost:8081/transporte/replica";

    @PostMapping
    public ResponseEntity<?> replicarTransporte (@RequestBody @Valid DTOReplicaTransporteBeneficio dto){
        TransportistaBeneficio transportistaBeneficio = transportistaBeneficioReposity.findByNitTransportista(dto.nitTransportista());
        TransporteBeneficio transporteBeneficio = new TransporteBeneficio(dto, transportistaBeneficio);
        transporteBeneficioReposity.save(transporteBeneficio);
        return ResponseEntity.ok(Map.of("mensaje","Transporte replicado"));

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTransporteBeneficio(@RequestBody @Valid DTOActualizarTransporteBeneficio dto){
        TransporteBeneficio transporteBeneficio = transporteBeneficioReposity
                .findByPlaca(dto.placa());
        transporteBeneficio.actualizarTransporteBeneficio(dto);

        DTOActualizarCatalogoTransporte dtoAgricultor = new DTOActualizarCatalogoTransporte(
                dto.placa(),
                dto.idEstado(),
                dto.observaciones(),
                dto.usuarioCreacion());

        String jwtToken = tokenService.generarToken(
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<DTOActualizarCatalogoTransporte> request = new HttpEntity<>(dtoAgricultor, headers);
        restTemplate.put(replicUrl, request);

        return ResponseEntity.ok(Map.of("mensaje", "Transporte actualizado"));

    }

    @GetMapping
    public ResponseEntity<Page<DTOListarTransporteBeneficio>> listarTransporteBeneficio(@PageableDefault(size =  10) Pageable paginacion ){
        return ResponseEntity.ok(transporteBeneficioReposity.findAll(paginacion).map(DTOListarTransporteBeneficio::new));
    }
}
