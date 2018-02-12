package com.acdn.rentalcar.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.acdn.rentalcar.model.Carro;
import com.acdn.rentalcar.model.ClasseCarro;
import com.acdn.rentalcar.model.Cliente;
import com.acdn.rentalcar.model.ClienteLogado;
import com.acdn.rentalcar.model.Reserva;
import com.acdn.rentalcar.repository.Carros;
import com.acdn.rentalcar.repository.ClassesCarros;
import com.acdn.rentalcar.repository.Clientes;
import com.acdn.rentalcar.repository.Reservas;

import org.apache.log4j.Logger;


@Controller
@RequestMapping("/acdn")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ClientesPainelController {
	
	@Autowired
	private ClienteLogado clienteLogado;
	
	private static Logger log = Logger.getLogger(ClientesPainelController.class);
	
	@Autowired
	private Clientes clientes;
	
	@Autowired
	private Reservas reservas;
	
	@Autowired
	private Carros carros;
	
	@Autowired
	private ClassesCarros classesCarros;
	
	private Carro locarCarro;
	
	@PostMapping("/meusdados")
	public ModelAndView atualizarCadastro(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return acessarMeusDados(cliente);
		}
		else{
			clientes.save(cliente);	
			gravarClienteNaSessao(cliente);
			
			attributes.addFlashAttribute("menssagem", "Seus dados foram atualizados!");
			return new ModelAndView("redirect:/acdn/meusdados");
		}
	}
	
	@GetMapping("/painel")
	public ModelAndView templatePainelCliente(Cliente cliente){
		if( !clienteEstaLogado() ){
			ModelAndView mv = new ModelAndView("rentalcar/cadastro");
			mv.addObject(cliente);
			return mv;		
		}else{
			gravarClienteNaSessao(cliente);
			
			ModelAndView mv = new ModelAndView("painel/minhaconta");
			mv.addObject(clienteLogado.getCliente());
			return mv;
		}
	}
	
	@GetMapping("/meusdados")
	public ModelAndView acessarMeusDados(Cliente cliente){
		ModelAndView mv = new ModelAndView("painel/meusdados");
		mv.addObject(clienteLogado.getCliente());
		return mv;
	}
	
	@PostMapping("/minhasenha")
	public ModelAndView atualizarMinhaSenha(Cliente cliente, RedirectAttributes attributes){
		if( !verificarSenha(cliente.getSenha(), cliente.getConf_senha()) ){
			attributes.addFlashAttribute("erro", "Senha não confere.");
			return new ModelAndView("redirect:/acdn/minhasenha");
		}
		else{
			clientes.save(cliente);		
			attributes.addFlashAttribute("menssagem", "Senha atualizada com sucesso!");
			return new ModelAndView("redirect:/acdn/minhasenha");
		}	
	}
	
	@GetMapping("/minhasenha")
	public ModelAndView acessarMinhaSenha(Cliente cliente){
		ModelAndView mv = new ModelAndView("painel/minhasenha");
		mv.addObject(clienteLogado.getCliente());
		return mv;
	}
	
	@GetMapping("/minhasreservas")
	public ModelAndView getReservasDoCliente(Cliente cliente){
		if( !clienteEstaLogado() ){
			return new ModelAndView("redirect:acdn/cadastro");	
		}else{
			ModelAndView mv = new ModelAndView("painel/minhasreservas");
			Reserva reserva = reservas.findByReservaAtiva(clienteLogado.getCliente().getCpf());
			mv.addObject(clienteLogado.getCliente());
			mv.addObject("detalheReserva", reserva);
			if(reserva != null){
				mv.addObject("valor", String.valueOf(reserva.getValorTotal()));
			}
			return mv;
		}
	}
	
	@GetMapping("/historicoreservas")
	public ModelAndView getHistoricoDasReservasDoCliente(Cliente cliente){
		ModelAndView mv = new ModelAndView("painel/historico-reservas");
		mv.addObject(clienteLogado.getCliente());
		mv.addObject("reservasDoCliente", reservas.findByReservaFinalizada(clienteLogado.getCliente().getCpf()));
		return mv;
	}
	
	@GetMapping("/novareserva")
	public ModelAndView novaReservaDoCliente(Cliente cliente){
		ModelAndView mv = new ModelAndView("painel/nova-reserva");
		mv.addObject(clienteLogado.getCliente());
		Reserva reserva = reservas.findByReservaAtiva(clienteLogado.getCliente().getCpf());
		if(reserva != null){
			mv.addObject("erro", "reserva ativa");
		}
		return mv;
	}
	
	@GetMapping("/detalharreserva")
	public ModelAndView detalharReservaDoCliente(Cliente cliente, Long numReserva){
		ModelAndView mv = new ModelAndView("painel/detalhar-reserva");
		Reserva reserva = reservas.findOne(numReserva);
		mv.addObject(clienteLogado.getCliente());
		mv.addObject("detalheReserva", reserva); 
		mv.addObject("valor", String.valueOf(reserva.getValorTotal()));
		return mv;
	}
	
	@GetMapping("/nextReserva")
	public ModelAndView nextReservaDoCliente(Cliente cliente, Long numClasse){
		ModelAndView mv = new ModelAndView("painel/next-reserva");
		List<Carro> lista = carros.findByCarroDisponivel(numClasse);
		if(lista.isEmpty()){
			mv.addObject("erro", "não há carros disponiveis");
		}else{
			ClasseCarro classe = classesCarros.findOne(numClasse);

			locarCarro = lista.get(0);
			
			Reserva reserva = new Reserva();
			
			mv.addObject(clienteLogado.getCliente());
			mv.addObject("classe", classe);
			mv.addObject("reserva", reserva);
			
		}
		
		return mv;
	}
	
	@PostMapping("/salvarReserva")
	public ModelAndView salvarReserva(Reserva reserva){
		ModelAndView mv = new ModelAndView("/painel/minhaconta");
		reserva.setCarro(locarCarro);
		reserva.setCliente(clienteLogado.getCliente());
		reservas.save(reserva);
		mv.addObject("menssagem", "Parabéns! Você realizou uma reserva com ACDN Rental Car.");
		mv.addObject("cliente",clienteLogado.getCliente());
		return mv;
	}
	
	public void gravarClienteNaSessao(Cliente cliente){
		clienteLogado.setCliente(cliente);
	}
	
	public boolean clienteEstaLogado(){
		if(clienteLogado.getCliente() != null){
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
}
