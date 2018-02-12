package com.acdn.rentalcar.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acdn.rentalcar.model.Carro;
import com.acdn.rentalcar.model.ClasseCarro;
import com.acdn.rentalcar.model.Cliente;
import com.acdn.rentalcar.repository.Carros;
import com.acdn.rentalcar.repository.ClassesCarros;

@Controller
@RequestMapping("/acdn")
public class CarrosController {
	
	private static Logger log = Logger.getLogger(ClientesPainelController.class);
	
	@Autowired
	public Carros carros;
	
	@Autowired
	public ClassesCarros classes;

	
	@GetMapping("/cadastrarcarro")
	public ModelAndView templateCadastrarCarro(Cliente cliente, Carro carro){
		ModelAndView mv = new ModelAndView("rentalcar/cadastro-carro");
		mv.addObject(carro);
		mv.addObject("classes", classes.findAll());
		if(carros != null){
			mv.addObject("carros", carros.findAll());
		}
		
		return mv;
	}
	
	@PostMapping("/cadastrarcarro")
	public ModelAndView salvarCarro(Cliente cliente, @Valid Carro carro, BindingResult result){
		
		ClasseCarro classe = classes.findByClasse(carro.getClasseCarro().getClasse());
		if(classe != null){
			carro.setClasseCarro(classe);
			carros.save(carro);
			return new ModelAndView("redirect:cadastrarcarro");
		}
		
		else if(result.hasErrors()){

			return templateCadastrarCarro(null, carro);
		}	
		
		return new ModelAndView("redirect:cadastrarcarro");	
		
	}
	
	@RequestMapping(value="/cadastrarcarro/{placa}", method = RequestMethod.GET) 
	public ModelAndView deletarCarro(@PathVariable("placa") String placa){
		carros.delete(placa);
		return new ModelAndView("redirect:/acdn/cadastrarcarro");
	}

}
