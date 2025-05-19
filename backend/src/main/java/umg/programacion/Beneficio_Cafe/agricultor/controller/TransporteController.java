package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import umg.programacion.Beneficio_Cafe.agricultor.transporte.*;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTranportistaReposity;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.CatalogoTransportista;
import umg.programacion.Beneficio_Cafe.agricultor.transportista.EstadoTransportistaReposity;

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

    @PostMapping // Crear nuevo transporte
    public ResponseEntity<?> crearTransporte(@RequestBody @Valid DTOCrearTransporte dto){
        if(transporteReposity.findByPlaca(dto.placa()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un transporte registrado con la placa " + dto.placa());
        }

        CatalogoTransportista transportista = tranportistaReposity.findByNitTransportista(dto.nitTransportista())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontro el transportista"));

        TipoPlaca tipoPlaca = tipoPlacaReposity.findByTipoPlaca(dto.tipoPlaca())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se encontro el tipo de placa"));

        CatalogoTransporte nuevo = new CatalogoTransporte(dto);
        nuevo.setIdTransportista(transportista);
        nuevo.setIdPlaca(tipoPlaca);

        transporteReposity.save(nuevo);
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

}
