package umg.programacion.Beneficio_Cafe.agricultor.controller.beneficio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import umg.programacion.Beneficio_Cafe.agricultor.piloto.DTOActualizarCatalogoPiloto;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.TokenService;
import umg.programacion.Beneficio_Cafe.beneficio.pilotoBenefio.*;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.TransportistaBeneficio;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.TransportistaBeneficioReposity;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/beneficio/piloto")
public class PilotoBeneficioController {
    @Autowired
    private PilotoBeneficioReposity pilotoBeneficioReposity;
    @Autowired
    private TransportistaBeneficioReposity transportistaBeneficioReposity;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TokenService tokenService;

    private final String replicUrl =  "http://localhost:8081/piloto/replica";

    @PostMapping
    public ResponseEntity<?> replicarPiloto(@RequestBody @Valid DTOReplicaPilotoBeneficio dto){
        TransportistaBeneficio transportistaBeneficio = transportistaBeneficioReposity.findByNitTransportista(dto.nitTransportista());
        PilotoBeneficio pilotoBeneficio = new PilotoBeneficio(dto, transportistaBeneficio);
        pilotoBeneficioReposity.save(pilotoBeneficio);
        return ResponseEntity.ok(Map.of("mensaje","Piloto replicado con exito"));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarPilotoBeneficio(@RequestBody @Valid DTOActualizarPilotoBeneficio dto){
        PilotoBeneficio pilotoBeneficio = pilotoBeneficioReposity
                .findByCui(dto.cui());
        pilotoBeneficio.actualizarPilotoBeneficio(dto);

        DTOActualizarCatalogoPiloto dtoAgricultor = new DTOActualizarCatalogoPiloto(
                dto.cui(),
                dto.idEstado(),
                dto.observaciones(),
                dto.usuarioCreacion()
        );

        String jwtToken = tokenService.generarToken(
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<DTOActualizarCatalogoPiloto> request = new HttpEntity<>(dtoAgricultor, headers);
        restTemplate.put(replicUrl, request);

        return ResponseEntity.ok(Map.of("mensaje", "Piloto actualizado"));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListarPilotoBeneficio>> listarPilotoBeneficio(@PageableDefault(size =  10) Pageable paginacion,
                                                                                @RequestParam(required = false) String cui,
                                                                                @RequestParam(required = false) Long estado){
        Page<PilotoBeneficio> resultado;

        if((cui == null || cui.isEmpty() ) && estado == null){
            resultado = pilotoBeneficioReposity.findAll(paginacion);
        } else if((cui != null && !cui.isEmpty()) && estado == null){
            resultado = pilotoBeneficioReposity.findByCuiContainingIgnoreCase(cui, paginacion);
        } else if((cui == null || cui.isEmpty()) && estado != null){
            resultado = pilotoBeneficioReposity.findByIdEstado_IdEstadoTransportista(estado, paginacion);
        } else {
            resultado = pilotoBeneficioReposity.findByCuiContainingIgnoreCaseAndIdEstado_IdEstadoTransportista(cui, estado, paginacion);
        }
        return ResponseEntity.ok(resultado.map(DTOListarPilotoBeneficio::new));
    }
}
