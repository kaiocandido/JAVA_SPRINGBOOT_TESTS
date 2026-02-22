package kaiocandido.demo.controller;

import kaiocandido.demo.entity.CarroEntity;
import kaiocandido.demo.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro){
        try {
            var carroSalvo = carroService.salvar(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }
}
