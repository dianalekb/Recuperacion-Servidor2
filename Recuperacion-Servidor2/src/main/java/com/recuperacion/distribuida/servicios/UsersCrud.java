package com.recuperacion.distribuida.servicios;

import java.util.List;

import com.recuperacion.distribuida.entidades.Users;



public interface UsersCrud {
	
	 public Users Buscar(int id);

	 public List<Users> buscarTodo();

}
