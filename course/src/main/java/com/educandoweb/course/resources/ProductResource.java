package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

/* 
 * annotation indicando que essa classe é um resource implementado
 * por um controller web.
 **/
@RestController 

/* 
 * annotation que indica o caminho do controlller rest, ou seja, o caminho
 * na qual esse controller rest responderá a requisições(esse é um endpoint)
 *  */
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;

	/* 
	 * O método findAll é um endpoint para acessar os usuários, o 
	 * tipo de retorna dele é o ResponseEntity, que é um tipo específico
	 * do spring para retornar respostas de requisições web.
	 * 
	 * ResponseEntity é um generics, ele espera um tipo de entrada.
	 * 
	 * @GetMapping - Annotation para indicar que esse método
	 * responde a requisições http get no endpoint /users.
	 *  */
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = productService.findAll();
		
		/* 
		 * ResponseEntity.ok() - retorna uma resposta de sucesso
		 * http colocando no body da resposta o tipo com a qual a
		 * classe ResponseEntity foi parametrizada.
		 * */
	    return ResponseEntity.ok().body(list);	
	}
	
	
	/*
	 * @GetMapping - Annotation para indicar que esse método
	 * responde a requisições http get no endpoint /users/id
	 * 
	 * @Pathvariable - annotation que serve para informar para 
	 * o spring que o parâmetro que chegará na url deve ser
	 * considerado como o parâmetro do método findById.
	 * 
	 * Quando é feita a chamada de um Product pelo id ou qualquer
	 * outro campo, o jpa automaticamente também traz os Orders associados
	 * a aquele Product,o jpa não isso de forma automatica quando eu estou
	 * no lado do um e outro lado é o dos muitos,para isso ser feito
	 * precisamos usar @OneToMany(mappedBy = "nomeDoAtributoDoOutroLadoDaAssociacao")
	 * 
	 * Quando você tem uma associação para muitos, o JPA não carrega
	 * o objeto para muitos, isso é oque chamamos de lazy loading, isso
	 * para não estourar a memória do seu computador.
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product user = productService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	  
}
