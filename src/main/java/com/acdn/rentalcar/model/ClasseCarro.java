package com.acdn.rentalcar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ClassesCarros")
public class ClasseCarro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idClasses;
	
	/*
	 * subcompacto, compacto, tamanho medio, tamanho grande, luxo
	 */
	@NotNull
	public String classe; 
	
	@NotNull
	public double valor;

	public Long getId() {
		return idClasses;
	}

	public void setId(Long id) {
		this.idClasses = id;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}	
	
	
}
