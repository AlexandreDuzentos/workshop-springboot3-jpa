package com.educandoweb.course.entities.enums;

public enum OrderStatus {
    
	/* O java por padrão atribui um valor numérico a cada um dos
	 * estados de um tipo enumerado, começando pelo zero.Porém isso
	 * pode deixar o nosso banco de dados errado, pois se num futuro
	 * um programador inexperiente adicionar um novo estado a esse
	 * tipo enumerado antes de qualquer local antes do último estado,
	 * os valores padrão atribuídos serão outros, oque deixará a representação
	 * numérica dos estados de um tipo enumerado imprevisível ou apenas
	 * difícil de prever, para resolver isso atruiremos manualmente
	 * os valores numéricos a cada um dos estados do tipo enumerado,
	 * isso para deixar explícito cada valor dos estados do meu tipo
	 * enumerado.
	 * */
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELLED(5);
	
	private int code;
	
	/* 
	 * O construtor do tipo enumerado é um caso especial, por ele
	 * poder ser private, ele é necessário para permitir essa atribuição
	 * manual de um código a cada um dos estados de um tipo
	 * enumerado.
	 * */
	private OrderStatus(int code) {
		this.code = code;
	}
	
	/*
	 * Método para tornar o atributo code do tipo enumerado
	 * acessível para o mundo exterior.
	 * */
	public int getCode() {
		return code;
	}
	
	/* 
	 * Método responsável por retornar um estado de um tipo
	 * enumerado dado um determinado código, caso o código dado
	 * não exista, ele retornará uma exceção, ou seja, ele
	 * é responsável por converter um objeto do tipo Integer
	 * para OrderStatus.
	 * */
	public static OrderStatus valueOf(int code) {
		/*
		 * Percorrendo todos os estados da minha enumeração.
		 * 
		 * o método values retorna um array contendo todos os
		 * estados da minha enumeração, cada um dos estados é
		 * do tipo OrderStatus.
		 * */
		for(OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}

