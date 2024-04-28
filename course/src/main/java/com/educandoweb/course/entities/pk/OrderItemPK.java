package com.educandoweb.course.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*
 * Essa é apenas uma classe auxiliar que serve de chave primária
 * composta formada pelo par Product e Order.
 * 
 * Sempre que eu precisar de uma classe auxiliar para ser uma chave
 * primária composta, ela estará no subpacote pk.
 * 
 * @Embeddable - annotation que informa para o jpa que essa é um classe
 * que representa uma chave primária composta.
 * */
@Embeddable
public class OrderItemPK implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* O par product e order formam uma chave primária
	 * composta que identifica um item de pedido(order item), 
	 * a classe OrderItem não possuirá uma chave primária própria,
	 * ela tem uma referência para Product e outra para Order.
	 * 
	 * O par de atributos product e order identificam tanto a chave
	 * primária quanto as chaves estrangeiras.
	 * */
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Order order;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	
	
	
	
}
