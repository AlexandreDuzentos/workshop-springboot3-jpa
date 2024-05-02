package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/* 
	 * Antes da versão 8 do java, usamos o tipo Date para trabalhar
	 * com datas, mas após a versão surgir o tipo Instant que é muito
	 * melhor para trabalhar com datas.
	 * 
	 * @JsonFormat - annotation para garantir que o moment seja exibido no Json
	 * no formato ISO 8601 com data-hora global com o fuso GMT/UTC.
	 * */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd'T'hh:mm:ss'Z'", timezone="GMT")
	private Instant moment;
	
	/* 
	 * Alteramos o tipo de da enumeração de OrderStatus para Integer
	 * para dizer explícitamente que estou gravando no banco de dados
	 * um número inteiro.
	 * */
	private Integer orderStatus;
	
	/* 
	 * @ManyToOne - annotation para imformar para o jpa que essa
	 * é um chave estrangeira, a chave estrageira é criada no lado
	 * muitos, que no caso corresponde a entidade Order.
	 * 
	 * Eu estando na classe Order, eu tenho uma associação muitos
	 * para um com a classe User.
	 * 
	 * @JoinColumn - annotation para informar para o jpa o nome
	 * do campo da chave estrangeira que será criada no banco de
	 * dados na tabela Order.
	 * 
	 * */
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;

	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	/* Na relação entre os objetos payment e Order, o objeto Order
	 * é a entidade independente, pois podemos ter um Order sem
	 * um Payment por conta multiplicidade entre eles, Order tem no
	 * mínino 0 payments e no máximo 1 Payment, ou seja, é opcional
	 * que o Order tenha payment, já no caso de Payment ela é a entidade
	 * dependente, pois não podemos ter um payment sem um Order, a multiplicidade
	 * entre eles é: Payment tem no mínimo um Order e no máximo um Order.
	 * 
	 * Por conta do Order ser o lado independente, o JPA não carrega
	 * o Payment associado a ele por padrão(automáticamente), para que 
	 * isso seja feito esse o lado independente da associação também
	 * precisa ser mapeado, daí a annotation
	 * @OneToOne(mappedBy = "nomedoatributodooutrolado").
	 * 
	 * No caso da associação um para um nós estamos mapeando as duas
	 * entidades para ter o mesmo id, como assim? se o Order for 
	 * código 5, o Payment desse Order também será código 5, e no
	 * caso de mapear relacionamento um para um o mesmo id é obrigatório
	 * colocar isso também cascade = CascadeType.ALL.
	 * */
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {}
	
	/* O atributo payment não foi colocado no construtor porque
	 * podemos ter um Order sem um payment, então não há obrigatoriedade
	 * de no construtor colocar o atributo payment, há outras formas de
	 * associar um Payment a um Order, um método
	 * na classe Order para associar um Payment a um Order, que é o
	 * método setPayment.
	 * */
	public Order(Long id, Instant moment,OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public OrderStatus getOrderStatus() {
		/* convertendo um valor do tipo Integer para OrderStatus */
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		/* 
		 * convertendo um valor do tipo OrderStatus para um
		 * valor do tipo Integer.
		 * */
		if(orderStatus != null) {
		   this.orderStatus = orderStatus.getCode();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	/* Método responsável por calcular o total de todos OrderItem
	 * associados ao Order.
	 * 
	 * O prefixo get é por conta do padrão usado pelo java EE para
	 * que o resultado desse método possa ser exibido no JSON.
	 *  */
	public double getTotal() {
		double sum = 0.0;
		for(OrderItem orderItem : items) {
			sum += orderItem.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
