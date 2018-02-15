package com.acdn.rentalcar.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class ClienteLogado {

	private String cpfDoClienteLogado;
	
	private String nomeCompleto;
	
	private Cliente cliente;
	
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpfDoClienteLogado() {
		return cpfDoClienteLogado;
	}

	public void setCpfDoClienteLogado(String cpfDoClienteLogado) {
		this.cpfDoClienteLogado = cpfDoClienteLogado;
	}
	
	
}
