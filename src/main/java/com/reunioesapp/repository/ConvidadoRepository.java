package com.reunioesapp.repository;



import org.springframework.data.repository.CrudRepository;

import com.reunioesapp.models.Convidado;
import com.reunioesapp.models.Reuniao;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByReuniao(Reuniao reuniao);
	Convidado findByRg(String rg);
}