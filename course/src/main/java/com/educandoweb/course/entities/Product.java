package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private Double price;
  private String imgUrl;
  
  /*  A instanciação é feita para a minha coleção não comece valendo
   *  null, ao instanciar, ela comecará vazia, porém instânciada.
   * 
   *  Estamos a usar a estrura Set, pois ela não admite repetições
   *  e não podemos ter um Product com Categories repetidas.
   * 
   *  @Transient - annotation para informar ao jpa para não
   *  interpretar esse propriedade.
   *  
   *  @JoinTable - annotation para específicar o nome da tabela de
   *  associação entre as entidades product e category, bem como
   *  o nome da chave estrangeira correspondente a entidade em
   *  que nos encontramos(Product), essa annotation deve estar em
   *  apenas uma das entidades que farão parte da associação muitos-para-muitos.
   *  
   *  joinColumns = @JoinColumn(name = "product_id") - seta o nome
   *  da chave estrangeira da entidade em que nos encontramos na tabela
   *  de associação "tb_product_category"
   *  
   *  inverseJoinColumns = @JoinColumn(name = "category_id") - seta o nome
   *  da chave estrangeira  outra entidade da associação muitos-para-muitos
   *  na tabela de associação "tb_product_category"
   * */
  
  @ManyToMany
  @JoinTable(name = "tb_product_category",
  joinColumns = @JoinColumn(name = "product_id"),
  inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories = new HashSet<>();
  
  @OneToMany(mappedBy = "id.product")
  private Set<OrderItem> items = new HashSet<>();
  
  public Product() {}

  /* 
   * Não colocamos construtores dentro do construtor, pois a
   * coleção já está sendo instânciada antes do construtor.
   * */
  public Product(Long id, String name, String description, Double price, String imgUrl) {
	this.id = id;
	this.name = name;
	this.description = description;
	this.price = price;
	this.imgUrl = imgUrl;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Set<Category> getCategories() {
		return categories;
	}
	
	/* Método que retorna uma lista de Orders associados ao Product*/
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> orders = new HashSet<>();
		for(OrderItem item : items) {
			orders.add(item.getOrder());
		}	
		return orders;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	

  
  
  
}
