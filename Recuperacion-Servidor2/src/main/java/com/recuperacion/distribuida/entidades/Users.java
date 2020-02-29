package com.recuperacion.distribuida.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "completed")
	private String completed;
	

public Users() {
		
	}


public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}


public Integer getUserId() {
	return userId;
}


public void setUserId(Integer userId) {
	this.userId = userId;
}


public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}


public String getCompleted() {
	return completed;
}


public void setCompleted(String completed) {
	this.completed = completed;
}


@Override
public String toString() {
	return "Users [id=" + id + ", userId=" + userId + ", title=" + title + ", completed=" + completed + "]";
}
	

}
