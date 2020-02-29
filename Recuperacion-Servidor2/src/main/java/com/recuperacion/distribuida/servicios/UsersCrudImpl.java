package com.recuperacion.distribuida.servicios;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.recuperacion.distribuida.entidades.Users;


@ApplicationScoped
@Transactional
public class UsersCrudImpl implements UsersCrud{
	
	@PersistenceContext
	private EntityManager em;
	
	
	  
	@Override
	  public Users Buscar(int id) {
		  Users users = em.find(Users.class, id);
				return users;
	  }

	@Override
	  public List<Users> buscarTodo() {
		  TypedQuery<Users> query = em.createQuery("Select e from Users e", Users.class);
	      List<Users> albums = query.getResultList();
	      return albums;

	  }
	  
	
	  
	
	  
}
