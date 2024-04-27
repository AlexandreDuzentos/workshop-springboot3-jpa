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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	/* 
	 * A instanciação é feita para a minha coleção não comece valendo
     * null, ao instanciar, ela começará vazia, porém instânciada.
     * 
	 * Estamos a usar a estrura Set, pois ela não admite repetições
	 * e não podemos ter um Category com Products repetidos.
	 * 
	 * @Transient - annotation para informar ao jpa para não
   *   interpretar esse propriedade.
   *   
   *   A função do método construtor é de inicializar os atributos
   *   do objeto, a coleção poderia estar dentro do construtor
   *   para ser inicializada, porém ela já foi inicializada antes dele.
   *   
	 * */
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();
	
	/* Construtor padrão(sem argumentos) */
	public Category() {
		
	}
	
	/* Construtor com argumentos */
	public Category(Long id, String name) {
		this.id = id;
        this.name = name;
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
	
	public Set<Product> getProducts() {
		return products;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
