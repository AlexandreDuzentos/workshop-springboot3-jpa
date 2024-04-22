package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;

/* 
 * Classe de configuração é uma classe auxiliar que irá fazer
 * algumas configurações da minha aplicação, ela não é um controller,
 * service e nem um repository.
 * 
 * classe de configuração específica para o perfil de teste.  
 * 
 * @Configuration - essa annotation informa para o spring que essa é
 * uma classe de configuração.
 * 
 * @Profile("test") - essa annotation informa que essa classe é uma
 * configuração específica para o perfil de teste.
 * 
 * Isso é para o spring rodar apenas essa configuração quando você
 * estiver no perfil de teste.
 * 
 * Essa classe servirá para popular o nosso banco de dados com alguns
 * dados, é para fazer o nosso database seeding, para isso, ela
 * terá uma dependência com os Repositories
 * 
 * Nas boas práticas dos princípios solid, quando um serviço depende
 * de outro, essa dependência tem de ser fraca, tem de ser
 * desacoplada, e essa injeção de dependência desacoplada pode ser
 * feita manualmente por meio do construtor, por meio de um padrão
 * factory, por meio de um método set, mas geralmente, quando usamos
 * um framework, esse framework tem um mecanismo de injeção de dependência
 * inplícito, é automático.
 *
 * */

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
   
	/*
	 * Injeção de dependência por framework(spring boot)
	 * 
	 * A annotaton @Autowired serve para informar para o spring que ele
	 * deve fazer uma injeção de dependência para esse atributo.
	 * 
	 * Fazer uma injeção de dependência é instânciar um objeto do tipo
	 * UserRepository associa-lo ao objeto userRepository.
	 * 
	 * Essa dependência nos ajudará a fazer o database seeding.
	 *  */
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	/*
	 * Todo código dentro desse método é executado quando a aplicação
	 * for executada.
	 * */
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.DELIVERED,u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID,u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		/* Salvando uma lista de Users, com o método padrão saveAll
		 * da interface UserRepository, o método asList retorna uma 
		 * lista de elementos.
		 * */
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		/* Salvando um a lista de Orders */
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
	}
}
