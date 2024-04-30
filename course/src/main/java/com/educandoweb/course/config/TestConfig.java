package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
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
	 * A annotation @Autowired serve para informar para o spring que ele
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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	/*
	 * Todo código dentro desse método é executado quando a aplicação
	 * for executada.
	 * */
	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.DELIVERED,u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID,u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		/* Salvando uma lista de Users, com o método padrão saveAll
		 * da interface UserRepository, o método asList retorna uma 
		 * lista de elementos.
		 * */
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		/* Salvando um a lista de Orders */
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		/* Adicionando uma Category a lista de categories
		 * associada a um Product, ou seja, estou associando
		 * categories a products.
		 * 
		 * Isso também poderia ser feito através de um método
		 * chamado addCategory da classe Product.
		 * */
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		/* Salvando novamente os products, porém agora eles já
		 * estão associados a suas respectivas categories*/
		productRepository.saveAll(Arrays.asList(p2, p3, p4, p5));
		
		/* Associando um objeto oi1 do tipo OrderItem a um objeto
		 * do tipo Order e a outro objeto do tipo Product. 
		 * */
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		
		/* salvando todos os orderItems.
		 * */
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
		
	}
}
