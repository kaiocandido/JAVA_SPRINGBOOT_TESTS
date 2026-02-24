package kaiocandido.demo.service;

import jakarta.persistence.EntityNotFoundException;
import kaiocandido.demo.entity.CarroEntity;
import kaiocandido.demo.repository.CarroRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    CarroRespository carroRespository;

    public CarroService(CarroRespository carroRespository) {
        this.carroRespository = carroRespository;
    }

    public CarroEntity salvar(CarroEntity carro){
        if (carro.getValorDiaria() <=0){
            throw new IllegalArgumentException("Preço da diaria não pode ser menor que 0");
        }
        return carroRespository.save(carro);
    }

    public CarroEntity atualizar(Long id, CarroEntity carro){
        var carroExiste = carroRespository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não existe"));

        carroExiste.setModelo(carro.getModelo());
        carroExiste.setValorDiaria(carro.getValorDiaria());
        carroExiste.setCarroAno(carro.getCarroAno());

        return carroRespository.save(carroExiste);
    }
    public void deleteCarro(long id){
        var carroDeletar = carroRespository.findById(id).orElseThrow(() -> new  EntityNotFoundException("Carro não existe"));
        carroRespository.deleteById(id);
    }

    public CarroEntity buscarPorId(long id){
        return carroRespository.findById(id).orElseThrow(() -> new EntityNotFoundException("Carro não existe"));
    }

    public List<CarroEntity> listar(){
        return carroRespository.findAll();
    }
}
