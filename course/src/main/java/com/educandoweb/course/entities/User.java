package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/* @Entity - annotation para fazer o mapeamento do JPA da nossa
 * entidade User.
 * 
 * @Table - annotation para definir o nome da tabela correspondente
 * a entidade user que será criado no banco de dados.
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
