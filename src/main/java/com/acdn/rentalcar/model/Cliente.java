package com.acdn.rentalcar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Cliente")
public class Cliente {
	
	@Id
	@NotBlank(message="{cpf.vazio}")
	@CPF(message="{cpf.invalido}")
	public String cpf;
	
	@NotBlank(message="{email.vazio}")
	@Email(message="{email.invalido}")
	public String email;
	
	@NotBlank(message="{nome.vazio}")
	public String nome;
	
	@NotBlank(message="{cnh.vazia}")
	@Size(max=11)
	public String cnh;
	
	@NotNull(message="{validadecnh.vazia}")
	@Future(message="{cnhvencida}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date validade_cnh;
	
	@NotBlank(message="{categoriacnh.vazia}")
	public String categoria_cnh;
	
	@NotBlank(message="{senha.vazia}")
	public String senha;
	
	@NotBlank(message="{senha.vazia}")
	public String conf_senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public Date getValidade_cnh() {
		return validade_cnh;
	}

	public void setValidade_cnh(Date validade_cnh) {
		this.validade_cnh = validade_cnh;
	}

	public String getCategoria_cnh() {
		return categoria_cnh;
	}

	public void setCategoria_cnh(String categoria_cnh) {
		this.categoria_cnh = categoria_cnh;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConf_senha() {
		return conf_senha;
	}

	public void setConf_senha(String conf_senha) {
		this.conf_senha = conf_senha;
	}
	
	
	
	
}
