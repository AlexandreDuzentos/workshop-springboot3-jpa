package com.educandoweb.course.services.exceptions;

/* 
 * Classe responsável por tratar exceções quando um recurso não
 * é achado no servidor.
 * 
 * A RuntimeException o compilador não obriga a tratar quando
 * lançada.
 * */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. "+ id);
	}

}
