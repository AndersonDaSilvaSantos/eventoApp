package com.reunioesapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;





@Entity
public class Convidado {

	@Id 
	@NotEmpty
	private String rg;
	
	@NotEmpty
	private String nomeConvidado;
	
	@ManyToOne
	private Reuniao reuniao;
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNomeConvidado() {
		return nomeConvidado;
	}
	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}
	public Reuniao getReuniao() {
		return reuniao;
	}
	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}
	
	
}
