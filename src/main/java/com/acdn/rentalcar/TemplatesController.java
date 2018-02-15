package com.acdn.rentalcar;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/acdn")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class TemplatesController {
	
	@GetMapping
	public ModelAndView iniciarSistema(){
		ModelAndView mv = new ModelAndView("rentalcar/index");
		return mv;
	}
	
}
