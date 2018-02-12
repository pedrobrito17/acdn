package com.acdn.rentalcar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Reserva")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long numReserva;
	
	@NotNull
	public int qtdDiarias;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date dataLocacao;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date dataDevolucao;
	
	@NotNull
	public String situacao; //ativa, atrasada, finalizada
	
	@NotNull
	public double valorTotal; //qtdDiaria * valor(tabela ClassesCarros)
	
	@ManyToOne
	@JoinColumn(name="placa")
	public Carro carro;
	
	@ManyToOne
	@JoinColumn(name="cpf")
	public Cliente cliente;

	public Long getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(Long numReserva) {
		this.numReserva = numReserva;
	}

	public int getQtdDiarias() {
		return qtdDiarias;
	}

	public void setQtdDiarias(int qtdDiarias) {
		this.qtdDiarias = qtdDiarias;
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
