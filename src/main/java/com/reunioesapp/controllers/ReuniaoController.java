package com.reunioesapp.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reunioesapp.models.Convidado;
import com.reunioesapp.models.Reuniao;
import com.reunioesapp.repository.ConvidadoRepository;
import com.reunioesapp.repository.ReuniaoRepository;

@Controller
public class ReuniaoController {

	@Autowired
	private ReuniaoRepository rr;
	
	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value="/cadastrarReuniao", method=RequestMethod.GET)
	public String form(){
		return "reuniao/formReuniao";
	}
	
	@RequestMapping(value="/cadastrarReuniao", method=RequestMethod.POST)
	public String form(@Valid Reuniao reuniao, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarReuniao";
		}
		
		rr.save(reuniao);
		attributes.addFlashAttribute("mensagem", "Reunião cadastrada com sucesso!");
		return "redirect:/cadastrarReuniao";
	}
	
	@RequestMapping("/reunioes")
	public ModelAndView listaReunioes(){
		ModelAndView mv = new ModelAndView("listaReunioes");
		Iterable<Reuniao> reunioes = rr.findAll();
		mv.addObject("reunioes", reunioes);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesReuniao(@PathVariable("codigo") long codigo){
		Reuniao reuniao = rr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("reuniao/detalhesReuniao");
		mv.addObject("reuniao", reuniao);
		
		Iterable<Convidado> convidados = cr.findByReuniao(reuniao);
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	/*@RequestMapping("/editarReuniao")
	public String editarReuniao(@PathVariable long codigo) {
		Reuniao reuniao = rr.findByCodigo(codigo);
		rr.update(reuniao);
		return "reuniao/formReuniao";
	}
	*/
	
	@RequestMapping("/deletarReuniao")
	public String deletarReuniao(long codigo){
		Reuniao reuniao = rr.findByCodigo(codigo);
		rr.delete(reuniao);
		return "redirect:/reunioes";
	}
	
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesReuniaoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Reuniao reuniao = rr.findByCodigo(codigo);
		convidado.setReuniao(reuniao);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg){
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);
		
		Reuniao reuniao = convidado.getReuniao();
		long codigoLong = reuniao.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
}	