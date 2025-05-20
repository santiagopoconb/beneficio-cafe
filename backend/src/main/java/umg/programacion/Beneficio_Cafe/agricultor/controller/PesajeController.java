package umg.programacion.Beneficio_Cafe.agricultor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import umg.programacion.Beneficio_Cafe.agricultor.pesaje.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pesaje")
public class PesajeController {
    @Autowired
    private MedidasPesoReposity medidasPesoReposity;
    @Autowired
    private PesajeReposity pesajeReposity;

    @PostMapping
    public ResponseEntity<?> crearPesaje(@RequestBody @Valid DTOCrearPesaje dtoCrearPesaje){
        MedidasPeso medidaPeso = medidasPesoReposity.findByIdMedida(dtoCrearPesaje.idMedidaPeso())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se encontró la medida seleccionada"));

        Pesaje nuevo = new Pesaje(dtoCrearPesaje);
        nuevo.setIdMedida(medidaPeso);

        pesajeReposity.save(nuevo);
        return ResponseEntity.ok(Map.of("mensaje", "El pesaje se ha creado con éxito"));
    }

    @GetMapping("/medidaPeso")
    public ResponseEntity<List<DTOMedidasPeso>> medidasPeso(){
        return ResponseEntity.ok(medidasPesoReposity.findAll()
                .stream().map(DTOMedidasPeso::new).toList());
    }
}
