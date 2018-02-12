package com.acdn.rentalcar.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acdn.rentalcar.model.Cliente;
import com.acdn.rentalcar.model.ClienteLogado;
import com.acdn.rentalcar.repository.Clientes;

@Controller
@RequestMapping("/acdn")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ClientesController {
	
	private static Logger log = Logger.getLogger(ClientesPainelController.class);
	
	@Autowired
	private ClienteLogado clienteLogado;
	
	@Autowired
	Clientes clientes;
	
	private Cliente clienteAutenticado;
	
	@PostMapping("/logar-cliente")
	public ModelAndView logar(Cliente cliente){
		//FAZ A AUTENTICAÇÃO DO ADMINISTRADOR OU DO CLIENTE
		if( logarAdministrador(cliente.getEmail(), cliente.getSenha()) ){
			return new ModelAndView("redirect:cadastrarcarro");
		}		
		if( logarCliente(cliente) ){
			gravarClienteNaSessao(this.clienteAutenticado);
			
			ModelAndView mv = new ModelAndView("redirect:painel");
			mv.addObject("cliente", this.clienteAutenticado);
			return mv;
		}
		if( !logarCliente(cliente) ){
			return templateCadastro(cliente).addObject("errologar", true);
		}
		return null;
	}

	@PostMapping("/cadastro")
	public ModelAndView cadastrarNovoCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes){
		//VERIFICA SE HÁ ERROS NO PREENCHIMENTO DO FORMULÁRIO
		if( verificarSenha(cliente.getSenha(), cliente.getConf_senha()) ){
			return templateCadastro(cliente).addObject("erro", "Senha não confere");
		}		
		if(result.hasErrors()){
			if(!cliente.getSenha().equals(cliente.getConf_senha())){
				return templateCadastro(cliente).addObject("erro", "Senha não confere.");
			}
			return templateCadastro(cliente);
		}
		clientes.save(cliente);		
		attributes.addFlashAttribute("menssagem", "Cliente cadastrado com sucesso!");
		return new ModelAndView("redirect:/acdn/cadastro");
	}
	
	@GetMapping("/cadastro")
	public ModelAndView templateCadastro(Cliente cliente){		
		if( clienteEstaLogado() ){
			ModelAndView mv = new ModelAndView("redirect:painel");
			mv.addObject(this.clienteLogado.getCliente());
			log.info("AQUI##################################"+clienteLogado.getCliente().getNome());
			return mv;
		} else{			
			ModelAndView mv = new ModelAndView("rentalcar/cadastro");
			mv.addObject(cliente);		
			return mv;			
		}
	}
	
	public Cliente autenticarCliente(Cliente acesso){
		Cliente cliente = clientes.findByEmailAndSenha(acesso.getEmail(), acesso.getSenha());
		
		if(cliente != null){
			return cliente;	
		}
		return null;
	}
	
	public boolean clienteEstaLogado(){
		if(this.clienteLogado.getCliente() != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean logarAdministrador(String email, String senha){
		if(email.equals("admin") && senha.equals("admin")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean logarCliente(Cliente cliente){
		this.clienteAutenticado = autenticarCliente(cliente);
		if(this.clienteAutenticado != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean verificarSenha(String senha, String conf_senha){
		if( !senha.equals(conf_senha) ){
			return true;
		}else{
			return false;
		}
	}
	
	public void gravarClienteNaSessao(Cliente cliente){
		this.clienteLogado.setCliente(cliente);
	}
	
}
