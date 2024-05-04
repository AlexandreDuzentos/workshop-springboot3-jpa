package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

/* @Component - essa annotation serve para registrar a minha classe
 * como um componente do spring.
 * 
 * @Service - essa annotation serve para informar para o spring que
 * esse é uma classe de serviço e também para registrar a minha classe
 * como um componente do spring.
 *  
 * quando você tem um objeto que vai ser injetado pelo mecanismo
 * de injeção de dependência do spring, a classe desse seu objeto
 * precisa estar registrada no mecanismo de injeção de dependência, isso
 * é que é fazer um component registration.
 *	
 * */
@Service
public class UserService {
   
	@Autowired
	private UserRepository userRepository;
	
	/* 
	 * método findAll repassando a chamada do método findAll para a data
	 * access layer.
	 * */
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	/* 
	 * método findById repassando a chamada do método findbyId para a data
	 * access layer.
	 *  */
	public User findById(Long id) {
		/* O objeto Opcional existe desde o java 8 */
		Optional<User> opt =  userRepository.findById(id);
		
		/* A operação get do objeto genérico Optional retorna
		 * um objeto do tipo com a qual a classe Optional foi
		 * parametrizada.
		 * 
		 * O método orElseThrown tenta chamar o método get, mas se
		 * não tiver usuário(User) na resposta da consulta ele
		 * lança uma exceção
		 * */
		return opt.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return userRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			if(userRepository.existsById(id)) {
				/*
				 * aqui pode ser lançada uma exceção do tipo DBIntegrityException que 
				   ocorre quando tenta-se deletar um registro que tem outro registros
				   associados a ele, por exemplo, um user que tem um Order associado
				   a ele não pode ser deletado, nesse caso o recurso para o id informado
				   existe, porém ele não ser deletado pois tem um registro pai associado a ele.
				*/
				userRepository.deleteById(id); 
			} else {
				/* Essa exceção será lançada quando não existir um
				 * recurso para o id informado, ou seja,um registro.
				 * */
			   throw new ResourceNotFoundException(id);
			}
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		/* O método getReferenceById instância um User com o id passado
		 * como argumento para ele, mas ele
		 * não vai para o banco de dados ainda, ele apenas deixa
		 * o objeto monitorado pelo jpa.
		 * 
		 * A exceção EntityNotFoundException será lançada quando o
		 * id informado para atulizar um registro, não 
		 * corresponder a nenhum registro existente.
		 * */
		try {
			User entity = userRepository.getReferenceById(id);
			
			updateData(entity, obj);
			
			return userRepository.save(entity);
		} catch(EntityNotFoundException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
		
	}

	/* Método responsável por atualizar os dados do objeto entity
	 * com os dados do objeto obj.
	 * */
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
}
