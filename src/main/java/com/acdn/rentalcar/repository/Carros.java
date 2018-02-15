package com.acdn.rentalcar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.acdn.rentalcar.model.Carro;

public interface Carros extends JpaRepository<Carro, String> {
	
	@Query(value = "SELECT * FROM carro r WHERE r.situacao = 'disponivel' AND r.id_classes = :id_classe",nativeQuery=true)
	List<Carro> findByCarroDisponivel(@Param("id_classe") Long id_classe );
}
