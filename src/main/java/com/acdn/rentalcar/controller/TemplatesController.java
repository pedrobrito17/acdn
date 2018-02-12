package com.acdn.rentalcar.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.acdn.rentalcar.model.Cliente;
import com.acdn.rentalcar.model.ClienteLogado;

@Controller
@RequestMapping("/acdn")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class TemplatesController {
	
	private static Logger log = Logger.getLogger(ClientesPainelController.class);
	
	@Autowired
	private ClienteLogado clienteLogado;
	
	@GetMapping
	public ModelAndView iniciarSistema(Cliente cliente){
		ModelAndView mv = new ModelAndView("rentalcar/index");
		if( clienteEstaLogado() ){
			mv.addObject("cliente", this.clienteLogado.getCliente());
		}
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView sairSistema(Cliente cliente){	
		fazerLogout();
		ModelAndView mv = new ModelAndView("rentalcar/index");
		return mv;
	}

	@GetMapping("/esquecisenha")
	public ModelAndView templateEsqueciSenha(Cliente cliente){
		ModelAndView mv = new ModelAndView("rentalcar/esquecisenha");
		if( clienteEstaLogado() ){
			mv.addObject("cliente", this.clienteLogado.getCliente());
		}
		return mv;
	}
	
	@GetMapping("/requisitos")
	public ModelAndView templateRequisitos(Cliente cliente){
		ModelAndView mv = new ModelAndView("rentalcar/requisitos");
		if( clienteEstaLogado() ){
			mv.addObject("cliente", this.clienteLogado.getCliente());
		}
		return mv;
	}
	
	@GetMapping("/frota")
	public ModelAndView templateFrota(Cliente cliente){
		ModelAndView mv = new ModelAndView("rentalcar/frota");
		if( clienteEstaLogado() ){
			mv.addObject("cliente", this.clienteLogado.getCliente());
		}
		return mv;
	}
	
	public boolean clienteEstaLogado(){
		if(this.clienteLogado.getCliente() != null){
			return true;
		}else{
			return false;
		}
	}
	
	public void fazerLogout(){
		this.clienteLogado.setCliente(null);
	}
	
	
}
