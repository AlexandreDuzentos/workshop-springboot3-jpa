package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

/* 
 * annotation indicando que essa classe é um resource implementado
 * por um controller web.
 **/
@RestController 

/* 
 * annotation que indica o caminho do controlller rest, ou seja, o caminho
 * na qual esse controller rest responderá a requisições(esse é um endpoint)
 *  */
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService OrderService;

	/* 
	 * O método findAll é um endpoint para acessar os usuários, o 
	 * tipo de retorna dele é o ResponseEntity, que é um tipo específico
	 * do spring para retornar respostas de requisições web.
	 * 
	 * ResponseEntity é um generics, ele espera um tipo de entrada.
	 * 
	 * @GetMapping - Annotation para indicar que esse método
	 * responde a requisições http get no endpoint /Orders.
	 *  */
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = OrderService.findAll();
		
		/* 
		 * ResponseEntity.ok() - retorna uma resposta de sucesso
		 * http colocando no body da resposta o tipo com a qual a
		 * classe ResponseEntity foi parametrizada.
		 * */
	    return ResponseEntity.ok().body(list);	
	}
	
	
	/*
	 * @GetMapping - Annotation para indicar que esse método
	 * responde a requisições http get no endpoint /Orders/id
	 * 
	 * @Pathvariable - annotation que serve para informar para 
	 * o spring que o parâmetro que chegará na url deve ser
	 * considerado como o parâmetro do método findById.
	 * 
	 * Quando é feita a chamada de um Order pelo id ou qualquer
	 * outro campo, o jpa automaticamente também traz o User associado
	 * a aquele order, isso quando eu estou no lado do muitos e outro
	 * lado é o lado do um.
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order Order = OrderService.findById(id);
		return ResponseEntity.ok().body(Order);
	}
	  
}
