package com.acdn.rentalcar.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="Carro")
public class Carro {
	
	@Id
	@NotBlank(message="Por favor digite a placa.")
	public String placa;
	
	@NotBlank(message="Por favor digite a marca do carro.")
	public String marca;
	
	@NotBlank(message="Por favor digite o modelo do carro.")
	public String modelo;
	
	@NotNull(message="Por favor digite o ano do modelo do carro.")
	public Integer ano;
	
	@NotNull(message="Por favor digite a kilometragem do carro.")
	public Integer km;
	
	public String descricao;
	
	public String situacao; //disponivel, alugado
	
	@ManyToOne
	@JoinColumn(name="idClasses")
	public ClasseCarro classeCarro;
	
	public Carro(){
		this.situacao = "disponivel";
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public ClasseCarro getClasseCarro() {
		return classeCarro;
	}

	public void setClasseCarro(ClasseCarro classeCarro) {
		this.classeCarro = classeCarro;
	}	
	
	

}
