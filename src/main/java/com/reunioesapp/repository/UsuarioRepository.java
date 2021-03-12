package com.reunioesapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.reunioesapp.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
}
