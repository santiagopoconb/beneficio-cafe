package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import umg.programacion.Beneficio_Cafe.agricultor.usuario.security.TokenService;
import umg.programacion.Beneficio_Cafe.beneficio.transportistaBeneficio.DTOReplicaTransportista;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/transportista")
public class TransportistaController {
    @Autowired
    private CatalogoTranportistaReposity transportistaReposity;
    @Autowired
    private EstadoTransportistaReposity estadoTransportistaReposity;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TokenService tokenService;

    private final String replicUrl =  "http://localhost:8081/beneficio/transportista";
    @Autowired
    private CatalogoTranportistaReposity catalogoTranportistaReposity;

    @GetMapping//Listar transportistas por pagina
    public ResponseEntity <Page<DTOListarTransportista>> listarTransportista(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(transportistaReposity.findAll(paginacion).map(DTOListarTransportista::new));
    }

    @GetMapping("/all")
    public  ResponseEntity<List<DTOListarTransportista>> listarTranportistaAll(){
        return ResponseEntity.ok(transportistaReposity.findAll()
                .stream()
                .map(DTOListarTransportista::new).toList());
    }

    @PostMapping
    public ResponseEntity<?> crearTransportista(@RequestBody @Valid DTOCrearTransportista dtoCrearTransportista){
        System.out.println("📢 Datos recibidos desde el frontend: " + dtoCrearTransportista);

        CatalogoTransportista nuevo = new CatalogoTransportista(dtoCrearTransportista);
        transportistaReposity.save(nuevo);

        DTOReplicaTransportista dtoReplicaTransportista = new DTOReplicaTransportista(
                dtoCrearTransportista.nitAgricultor(),
                dtoCrearTransportista.nitTransportista(),
                dtoCrearTransportista.nombreTransportista(),
                dtoCrearTransportista.usuarioCreacion());

        String jwtToken = tokenService.generarToken(
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<DTOReplicaTransportista> request = new HttpEntity<>(dtoReplicaTransportista, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(replicUrl, request, String.class);

        return ResponseEntity.ok(Map.of("mensaje","Transportista creado correctamente"));
    }

    @PutMapping("/replica")
    public ResponseEntity replicaTransportista(@RequestBody @Valid DTOActualizarCatologoTransportista dto){
        CatalogoTransportista transportistaAgricultor = catalogoTranportistaReposity.findByNitTransportista(dto.nitTransportista())
                .orElseThrow(() -> new RuntimeException("No existe una transportista con el nit"));
        transportistaAgricultor.actualizarCatalogoTransporista(dto);
        catalogoTranportistaReposity.saveAndFlush(transportistaAgricultor);
        return ResponseEntity.ok(Map.of("mensaje","Transportista actualizado"));
    }
}
