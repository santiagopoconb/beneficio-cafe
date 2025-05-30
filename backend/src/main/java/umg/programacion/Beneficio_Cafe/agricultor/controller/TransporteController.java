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
import umg.programacion.Beneficio_Cafe.agricultor.transporte.*;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTranportistaReposity;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTransportista;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.TokenService;
import umg.programacion.Beneficio_Cafe.beneficio.transporteBeneficio.DTOReplicaTransporteBeneficio;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/transporte")
public class TransporteController {
    @Autowired
    private CatalogoTransporteReposity transporteReposity;
    @Autowired
    private CatalogoTranportistaReposity tranportistaReposity;
    @Autowired
    private TipoPlacaReposity tipoPlacaReposity;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RestTemplate restTemplate;

    private final String replicUrl =  "http://localhost:8081/beneficio/transporte";
    @Autowired
    private CatalogoTransporteReposity catalogoTransporteReposity;

    @PostMapping // Crear nuevo transporte
    public ResponseEntity<?> crearTransporte(@RequestBody @Valid DTOCrearTransporte dto){
        //System.out.println("📢 Datos transporte recibidos desde el frontend: " + dto);
        if(transporteReposity.findByPlaca(dto.placa()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Ya existe un transporte registrado con la placa " + dto.placa()));
        }

        CatalogoTransportista transportista = tranportistaReposity.findByNitTransportista(dto.nitTransportista())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontro el transportista"));

        TipoPlaca tipoPlaca = tipoPlacaReposity.findByTipoPlaca(dto.tipoPlaca())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontro el tipo de placa"));

        CatalogoTransporte nuevo = new CatalogoTransporte(dto);
        nuevo.setIdTransportista(transportista);
        nuevo.setIdPlaca(tipoPlaca);
        transporteReposity.save(nuevo);

        DTOReplicaTransporteBeneficio replica = new DTOReplicaTransporteBeneficio(
                transportista.getNitTransportista(),
                tipoPlaca.getTipoPlaca(),
                dto.placa(),
                dto.marca(),
                dto.color(),
                dto.linea(),
                dto.modelo(),
                dto.usuarioCreacion(),
                dto.observaciones());

        String jwtToken = tokenService.generarToken(
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<DTOReplicaTransporteBeneficio> request = new HttpEntity<>(replica, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(replicUrl, request, String.class);

        return ResponseEntity.ok(Map.of("mensaje","El Transporte se ha creado con exito"));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListarTransporte>> listarTransporte(Pageable paginacion){
        return ResponseEntity.ok(transporteReposity.findAll(paginacion).map(DTOListarTransporte::new));
    }

    @GetMapping("/tipoPlaca")
    public ResponseEntity<List<DTOTipoPlaca>> listaTipoPlaca(){
        return ResponseEntity.ok(tipoPlacaReposity.findAll()
                .stream()
                .map(DTOTipoPlaca::new).toList());
    }

    @PutMapping("/replica")
    public ResponseEntity replicaTransporte(@RequestBody @Valid DTOActualizarCatalogoTransporte dto){
        CatalogoTransporte transporteAgricultor = transporteReposity.findByPlaca(dto.placa())
                .orElseThrow(()-> new RuntimeException("No se encontro el transporte"));
        transporteAgricultor.actualizarCatalogoTransporte(dto);
        transporteReposity.saveAndFlush(transporteAgricultor);
        return ResponseEntity.ok(Map.of("mensaje","El transporte se ha actualizado con exito"));
    }

}
