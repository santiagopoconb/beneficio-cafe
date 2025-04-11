package umg.programacion.Beneficio_Cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.programacion.Beneficio_Cafe.agricultor.CatalogoTranportistaReposity;
import umg.programacion.Beneficio_Cafe.agricultor.DTOListarTransportista;
import org.springframework.data.domain.*;
@RestController
@RequestMapping("/transportista")
public class TransportistaController {
    @Autowired
    private CatalogoTranportistaReposity transportistaReposity;

    @GetMapping
    public ResponseEntity <Page<DTOListarTransportista>> listarTransportista(Pageable paginacion){
        return ResponseEntity.ok(transportistaReposity.findAll(paginacion).map(DTOListarTransportista::new));
    }
}
