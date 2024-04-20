package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Order;

/* A interface UserRepository está herdando o contrato definido na
 * interface JpaRepository e esse contrato já tem uma implementação
 * padrão para acesso a dados, ou seja, ela possui default ou
 * defender methods para acesso a dados para a entidade passada como
 * parâmetro para a interface JpaRepository.
 * 
 * @Repository - essa annotation serve para informar para o spring que
 * esse é uma classe de repositório e também para registrar a minha
 * classe como um componente do spring, porém ela aqui é opcional, pois
 * a interface da qual ela herda já está registrada como um componente
 * do spring, logo ela também é um componente do spring devido a herança.
 *  */
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
