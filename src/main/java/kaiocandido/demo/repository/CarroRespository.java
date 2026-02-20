package kaiocandido.demo.repository;

import kaiocandido.demo.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRespository extends JpaRepository<CarroEntity, Long> {
    List<CarroEntity> findByModelo(String modelo);
}
