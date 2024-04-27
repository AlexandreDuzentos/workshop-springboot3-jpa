package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/* @Entity - annotation para fazer o mapeamento do JPA da nossa
 * entidade User, para que está seja uma entidade do meu sistema
 * gerenciada pelo JPA.
 * 
 * Ao importar o pacote relacionado a annotation @Entity havia duas
 * opções, sendo uma a jakarta.persistence.Entity e a outra
 * a org.hibernate.annotations.entity, a primeira é a especificação
 * do jpa e a segunda a implementação do jpa que foi baixada pelo
 * maven, nós sempre vamos dar preferência para a específicação, é
 * sempre bom fazer a sua classe depender da especificação e não da
 * implementação.
 * 
 * @Table - annotation para definir o nome da tabela correspondente
 * a entidade user que será criado no banco de dados.
 * 
 * Personalizamos o nome da tabela a ser criada pelo JPA no banco de
 * dados para tb_user, pois o nome dessa entidade(User) é uma palavra
 * reservada no banco de dados.
 * 
 * Entidades estão implementando Serializable pois nesse caso elas são
 * objetos persistentes, ou seja, eles serão trafegados pela rede
 * na hora de realizar alguma operação de persistência no banco de
 * dados, como por exemplo, a operação saveAll da interface
 * JPARepository.
 * */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 /* 
	  * @Id - annotation para informar para o JPA qual é a chave 
	  * primária da tabela no banco de dados.
	  * 
	  * @GeneratedValue - annotation para informar para o JPA que
	  * o valor da chave primária é auto-incrementável(auto-gerado)
	  * */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String name;
	  private String email;
	  private String phone;
	  private String password;
	  
	  /* Um objeto cujo tipo é uma coleção não deve possuir o 
	   * método set, pois ele, trocaria a coleção, e esse
	   * não é um comportamento desejável para o sistema.
	   * 
	   * Deve ter apenas o método get.
	   * 
	   * O lado do um para muitos também pode ser mapeado no jpa,
	   * é opcional, caso você queira acessar um objeto do tipo
	   * User e automaticamente acessar os Orders associados ao user.
	   * 
	   * @OneToMany - essa annotation serve para informar para o
	   * jpa que esse é o lado um da associação.
	   * 
	   * o mappedBy recebe o nome do atributo que tem lá do outro
	   * lado da associação.
	   * */
	  
	  /*
	   * Ocorrerá um erro de recursão infinita ou seja de loop
	   * infinito quando uma requisição for feita para o resource
	   * User, por que entre a entidade Order e User existe uma 
	   * associação de mão dupla ou bidirecional, dentro de Order temos uma referência
	   * para User e dentro de User temos uma referência para uma lista de Orders e
	   * então na hora de serializar os objetos para json a nossa
	   * biblioteca de serialização fica chamando, o usuário chama o Order e o
	   * Order chama o User e fica nesse loop infinito.
	   * 
	   * 
	   * @JsonIgnore - é uma annotation que serve para evitar o error de
	   * recursão infinita, ela deve ser colocada em um dos dois lados
	   * da associação pelo menos, pois quando é feita chamada de um user
	   * os orders associados a ele também são carregados e cada um desses
	   * Orders também chama o User associado a ele, daí o loop infinito,
	   * então essa annotation já impede que os Orders associados ao
	   * user sejam carregados.
	   * */
	  @JsonIgnore
	  @OneToMany(mappedBy = "client")
	  private List<Order> orders = new ArrayList<>();
	  
	  public User() {
	  }
	
	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	public List<Order> getOrders() {
		return orders;
	}


  
  
}
