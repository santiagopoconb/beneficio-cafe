package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import umg.programacion.Beneficio_Cafe.agricultor.piloto.*;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTranportistaReposity;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTransportista;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.EstadoTransportistaReposity;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.TokenService;
import umg.programacion.Beneficio_Cafe.beneficio.pilotoBenefio.DTOReplicaPilotoBeneficio;
import umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio.DTOReplicaTransporteBeneficio;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/piloto")
public class PilotoController {
    @Autowired
    private TipoLicenciaReposity tipoLicenciaReposity;
    @Autowired
    private CatalogoTranportistaReposity tranportistaReposity;
    @Autowired
    private CatalogoPilotoReposity pilotoReposity;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RestTemplate restTemplate;

    private final String replicUrl =  "http://localhost:8081/beneficio/piloto";

    @PostMapping
    public ResponseEntity<?> crearPiloto(@RequestBody @Valid DTOCrearPiloto dto) {
        System.out.println("📢 Datos piloto recibidos desde el frontend: " + dto);
        if(pilotoReposity.findByCui(dto.cui()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ya existe un piloto registrado con el CUI " + dto.cui());
        }

        CatalogoTransportista transportista = tranportistaReposity.findByNitTransportista(dto.nitTransportista())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontro el transportista"));

        TipoLicencia tipoLicencia = tipoLicenciaReposity.findByTipoLicencia(dto.tipoLicencia())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontro el tipo de licencia"));

        CatalogoPiloto nuevo = new CatalogoPiloto(dto);
        nuevo.setIdTransportista(transportista);
        nuevo.setTipoLicencia(tipoLicencia);
        pilotoReposity.save(nuevo);

        DTOReplicaPilotoBeneficio replica = new DTOReplicaPilotoBeneficio(
          transportista.getNitTransportista(),
          dto.cui(),
          dto.nombre(),
          dto.fechaNacimiento(),
          dto.fechaVencimiento(),
            dto.tipoLicencia(),
            dto.usuarioCreacion(),
            dto.observaciones());

        String jwtToken = tokenService.generarToken(
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<DTOReplicaPilotoBeneficio> request = new HttpEntity<>(replica, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(replicUrl, request, String.class);


        return ResponseEntity.ok(Map.of("mensaje","El piloto se ha creado con exito"));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListarPiloto>> listarPiloto(Pageable paginacion){
        return ResponseEntity.ok(pilotoReposity.findAll(paginacion).map(DTOListarPiloto::new));
    }

    @GetMapping("/tipoLicencia")
    public ResponseEntity<List<DTOTipoLicencia>> tipoLicencia(){
        return ResponseEntity.ok(tipoLicenciaReposity.findAll()
                .stream()
                .map(DTOTipoLicencia::new).toList());
    }

    @PutMapping("/replica")
    public ResponseEntity replicaCatalogoPiloto(@RequestBody @Valid DTOActualizarCatalogoPiloto dto){
        CatalogoPiloto pilotoAgricultor = pilotoReposity.findByCui(dto.cui())
                .orElseThrow(()-> new RuntimeException("No se encontro el piloto"));
        pilotoAgricultor.actualizarCatalogoPiloto(dto);
        pilotoReposity.saveAndFlush(pilotoAgricultor);
        return ResponseEntity.ok(Map.of("mensaje","El piloto se ha actualizado con exito"));
    }
}
