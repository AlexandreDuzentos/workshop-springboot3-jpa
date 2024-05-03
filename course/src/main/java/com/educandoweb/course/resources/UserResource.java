package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

/* 
 * annotation indicando que essa classe é um resource implementado
 * por um controller web.
 **/
@RestController 

/* 
 * annotation que indica o caminho do controlller rest, ou seja, o caminho
 * na qual esse controller rest responderá a requisições.
 * 
 * Esse é o nosso controller web que responde no caminho users
 *  */
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;

	/* 
	 * O método findAll é um endpoint para acessar os usuários, o 
	 * tipo de retorna dele é o ResponseEntity, que é um tipo específico
	 * do spring para retornar respostas de requisições web.
	 * 
	 * ResponseEntity é um generics, ele espera um tipo de entrada.
	 * 
	 * @GetMapping - Annotation para indicar que esse método
	 * responde a requisições http get no endpoint /users.
	 * 
	 * No padrão rest, o método usado para recuperar registros é
	 * o get.
	 *  */
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = userService.findAll();
		
		/* 
		 * ResponseEntity.ok() - retorna uma resposta de sucesso
		 * http colocando no body da resposta o tipo com a qual a
		 * classe ResponseEntity foi parametrizada.
		 * */
	    return ResponseEntity.ok().body(list);	
	}
	
	
	/*
	 * @GetMapping - Annotation para indicar que esse método
	 * responde a requisições http get no endpoint /users/id,
	 * indica que a requisição aceitará um id dentro da Url.
	 * 
	 * @Pathvariable - annotation que serve para informar para 
	 * o spring que o parâmetro que chegará na url deve ser
	 * considerado como o parâmetro do método findById.
	 * 
	 * Quando é feita a chamada de um User pelo id ou qualquer
	 * outro campo, o jpa automaticamente também traz os Orders associados
	 * a aquele User,o jpa não isso de forma automatica quando eu estou
	 * no lado do um e outro lado é o dos muitos,para isso ser feito
	 * precisamos usar o annotation @OneToMany(mappedBy = "nomeDoAtributoDoOutroLadoDaAssociacao")
	 * 
	 * Quando você tem uma associação para muitos, o JPA não carrega
	 * o objeto para muitos, isso é oque chamamos de lazy loading, isso
	 * para não estourar a memória do seu computador.
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	/* @PostMapping - annotation para indicar que esse método atende a requisições
	 * via post
	 * 
	 * @RequestBody - annotation para indicar que o objeto passado
	 * com parâmetro para o método insert chegará no formato JSON
	 * e será desserializado para um objeto User pela minha
	 * biblioteca de serialização e desserialização(para json).
	 * 
	 * O método usado para salvar dados no padrão rest é o post.
	 *  */
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		/* Reaproveitando a variável obj do tipo User que foi passada
		 * como parâmetro para o método, variáveis declaradas no parâmetro
		 * do método pertencem ao escopo do método.
		 * 
		 * Ordem de execução: primeiro é executada a operação e segundo
		 * o resultado da operação é atribuído para a variável obj.
		 * */
		obj = userService.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	/* @DeleteMapping - annotation para indicar que esse método receberá
	 * requisições http via delete.
	 * 
	 * O métod usado para deletar dados no padrão rest é o Delete.
	 * */
	@DeleteMapping(value = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
    	 userService.delete(id);
    	 
    	 /*
    	  * O método noContent está sendo usado porque a resposta
    	  * a uma requisição via delete feita no padrão rest é sem corpo, ele 
    	  * indica isso e o código de resposta de uma resposta sem
    	  * corpo é o 204, ele já tratará disso também.
    	  * */
    	 return ResponseEntity.noContent().build();
     }
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = userService.update(id, obj);
		
		return ResponseEntity.ok().body(obj);
	}
	
	  
}
