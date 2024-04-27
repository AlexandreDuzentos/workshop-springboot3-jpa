package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

/* A interface UserRepository está herdando o contrato definido na
 * interface JpaRepository e esse contrato já tem uma implementação
 * padrão para acesso a dados, ou seja, ela possui default ou
 * defender methods para acesso a dados para a entidade passada como
 * parâmetro para a interface JpaRepository.
 * 
 * A interface JpaRepository é parametrizada por tipo, recebe dois tipos,
 * sendo o primeiro a entidade e o segundo o tipo do id da entidade.
 * 
 * @Repository - essa annotation serve para informar para o spring que
 * esse é uma classe de repositório e também para registrar a minha
 * classe como um componente do spring, porém ela aqui é opcional, pois
 * a interface da qual ela herda já está registrada como um componente
 * do spring, logo ela também é um componente do spring devido a herança.
 *  */
public interface UserRepository extends JpaRepository<User, Long> {
    
}
