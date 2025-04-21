package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import umg.programacion.Beneficio_Cafe.agricultor.piloto.*;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTranportistaReposity;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTransportista;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.EstadoTransportistaReposity;

@RestController
@RequestMapping("/piloto")
public class PilotoController {
    @Autowired
    private TipoLicenciaReposity tipoLicenciaReposity;
    @Autowired
    private CatalogoTranportistaReposity tranportistaReposity;
    @Autowired
    private CatalogoPilotoReposity pilotoReposity;

    @PostMapping
    public ResponseEntity<?> crearPiloto(@RequestBody @Valid DTOCrearPiloto dto) {
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
        return ResponseEntity.ok("El piloto se ha creado con exito");
    }

    @GetMapping
    public ResponseEntity<Page<DTOListarPiloto>> listarPiloto(Pageable paginacion){
        return ResponseEntity.ok(pilotoReposity.findAll(paginacion).map(DTOListarPiloto::new));
    }
}
