package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	 * */
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	public Order() {}
	
	public Order(Long id, Instant moment, User client) {
		this.id = id;
		this.moment = moment;
		this.client = client;
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
	
	
}
