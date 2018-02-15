package com.acdn.rentalcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acdn.rentalcar.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, String> {
	
	Cliente findByEmailAndSenha(String email, String senha);
	
}
