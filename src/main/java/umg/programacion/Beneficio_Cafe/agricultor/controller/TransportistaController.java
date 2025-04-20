package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.*;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/transportista")
public class TransportistaController {
    @Autowired
    private CatalogoTranportistaReposity transportistaReposity;
    @Autowired
    private EstadoTransportistaReposity estadoTransportistaReposity;

    @GetMapping//Listar transportistas
    public ResponseEntity <Page<DTOListarTransportista>> listarTransportista(Pageable paginacion){
        return ResponseEntity.ok(transportistaReposity.findAll(paginacion).map(DTOListarTransportista::new));
    }

    @PostMapping
    public ResponseEntity<?> crearTransportista(@RequestBody @Valid DTOCrearTransportista dtoCrearTransportista){
        CatalogoTransportista nuevo = new CatalogoTransportista(dtoCrearTransportista);
        transportistaReposity.save(nuevo);
        return ResponseEntity.ok("Transportista creado correctamente");
    }
}
