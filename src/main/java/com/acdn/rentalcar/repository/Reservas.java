package com.acdn.rentalcar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.acdn.rentalcar.model.Reserva;

public interface Reservas extends JpaRepository<Reserva, Long> {
	
	@Query(value = "SELECT * FROM reserva r WHERE r.cpf = :cpf AND r.situacao='ativa'",nativeQuery=true)
	Reserva findByReservaAtiva(@Param("cpf") String cpf);
	
	
	@Query(value = "SELECT * FROM reserva r WHERE r.cpf = :cpf AND r.situacao='finalizada'",nativeQuery=true)
	List<Reserva> findByReservaFinalizada(@Param("cpf") String cpf);

	
}
