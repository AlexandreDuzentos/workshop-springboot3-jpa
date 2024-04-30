package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* O atributo identificador da classe OrderItem é uma outra classe
	 * auxiliar cujo seus atributos são formados pelo par de atributos
	 * Order e Product, isso porque o atributo identificador de um 
	 * orderItem precisa ser uma chave primária composta formada pelo
	 * order e pelo item do order(Product).
	 * */
	
	/* Não foi feito um getter direto para o atributo id, para que
	 * não fique confuso para o mundo exterior e para evitar o encadeamento.
	 * 
	 * Por exemplo se tivesses um getters para o id teriamos o seguinte
	 * no consumidor da classe atual(OrderItem)
	 *  
	 *  OrderItem orderItem = new OrderItem();
	 *  
	 *  orderItem.getId().getOrder();
	 *  
	 *  orderItem.getId().getProduct();
	 *  
	 *  Então a solução foi fazer getters para acessar cada um
	 *  dos objetos da classe OrderItemPK de forma individual na
	 *  própria classe(OrderItem).
	 *  
	 *  Desse modo teremos o seguinte num consumidor da classe
	 *  OrderItem:
	 *  
	 *  OrderItem orderItem = new OrderItem();
	 *  
	 *  orderItem.getOrder();
	 *  orderItem.getProduct();
	 *  
	 *  @EmbeddedId - annotation para informar para o jpa que a chave
	 *  primária é uma chave composta ou seja, é composta por dois
	 *  campos.
	 *  
	 *  Sempre que formos utilizar uma classe auxiliar que é o id
	 *  composto, você instância-la para que seu objeto não esteja
	 *  valendo null.
	 * */
	
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	/* O atributo price está sendo repetido na classe atual, para que
	 * caso num futuro o preço do Product mude na tabela Product, eu
	 * tenha um histórico de quanto custava o Product na altura em que
	 * ele foi pedido(Ordered).
	 * */
	private Double price;
	private Integer quantity;
	
	public OrderItem() {}

	/* 
	 * O id não será passado para o construtor da forma convencional.
	 * */
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.price = price;
		this.quantity = quantity;
	}
	
	/* A annotation foi colocada aqui, porque o atributo do tipo Order 
	 * não está diretamente na classe OrderItem, então devemos
	 * colocar a annotation por cima do método que retorna os Orders,
	 * afim de parar associação de mão dupla.
	 * */
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
