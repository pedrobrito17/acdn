package com.acdn.rentalcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acdn.rentalcar.model.ClasseCarro;

public interface ClassesCarros extends JpaRepository<ClasseCarro, Long> {
	
	ClasseCarro findByClasse(String classe);

}
