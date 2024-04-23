package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

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
public class ProductService {
   
	@Autowired
	private ProductRepository productRepository;
	
	/* 
	 * método findAll repassando a chamada do método findAll para a data
	 * access layer.
	 * */
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	/* 
	 * método findById repassando a chamada do método findbyId para a data
	 * access layer.
	 *  */
	public Product findById(Long id) {
		/* O objeto Opcional existe desde o java 8 */
		Optional<Product> opt =  productRepository.findById(id);
		
		/* A operação get do objeto genérico Optional retorna
		 * um objeto do tipo com a qual a classe Optional foi
		 * parametrizada.
		 * */
		return opt.get();
	}
}
