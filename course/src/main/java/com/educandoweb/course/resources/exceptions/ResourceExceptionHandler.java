package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/*
 *  É nessa classe onde será dado o tratamento manual para erros.
 *  
 *  @ControllerAdvice - essa annotation vai interceptar as exceções
 *  que acontecerem para que esse objeto possa efetuar um possível
 *  tratamento, é meio de uma sobrescrita do tratamento padrão de
 *  exceções do spring.
 * */

@ControllerAdvice
public class ResourceExceptionHandler {
 
	/* @ExceptionHandler - essa annotation fará com que esse método
	 * intercepte a requisição que deu exceção para ela cair aqui e 
	 * esse método sobrescrever o tratamento de exceções padrão
	 * do spring, tratando a exeção lançada com uma exceção personalizada
	 * (ResourceNotFoundException).
	 * 
	 * ela recebe como argumento o nome da exceção da será lançada.
	 * */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		 String error = "Resource not found";
		 HttpStatus status = HttpStatus.NOT_FOUND;
		 StandardError er = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		 
		 /*
		  * Retornando uma resposta com um código de resposta HTTP
		  * personalizado.
		  * */
		 return ResponseEntity.status(status).body(er);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		 String error = "Database error";
		 HttpStatus status = HttpStatus.BAD_REQUEST;
		 StandardError er = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		 
		 /*
		  * Retornando uma resposta com um código de resposta HTTP
		  * personalizado.
		  * */
		 return ResponseEntity.status(status).body(er);
	}
}
