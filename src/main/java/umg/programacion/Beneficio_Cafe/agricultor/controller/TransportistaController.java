package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;


import java.time.LocalDateTime;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/transportista")
public class TransportistaController {
    @Autowired
    private CatalogoTranportistaReposity transportistaReposity;
    @Autowired
    private EstadoTransportistaReposity estadoTransportistaReposity;

    @GetMapping//Listar transportistas
    public ResponseEntity <Page<DTOListarTransportista>> listarTransportista(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(transportistaReposity.findAll(paginacion).map(DTOListarTransportista::new));
    }

    @PostMapping
    public ResponseEntity<?> crearTransportista(@RequestBody @Valid DTOCrearTransportista dtoCrearTransportista){
        CatalogoTransportista nuevo = new CatalogoTransportista(dtoCrearTransportista);
        transportistaReposity.save(nuevo);
        return ResponseEntity.ok(Map.of("mensaje","Transportista creado correctamente"));
    }
}
