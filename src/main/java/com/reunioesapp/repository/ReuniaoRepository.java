package com.reunioesapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.reunioesapp.models.Reuniao;

public interface ReuniaoRepository extends CrudRepository<Reuniao, String> {
	Reuniao findByCodigo(long codigo);

	//void update(Reuniao reuniao);
	
	
}
