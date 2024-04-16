package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

/* A interface UserRepository está herdando o contrato definido na
 * interface JpaRepository e esse contrato já tem uma implementação
 * padrão para acesso a dados, ou seja, ela possui default ou
 * defender methods para acesso a dados para a entidade passada como
 * parâmetro para a interface JpaRepository. 
 *  */
public interface UserRepository extends JpaRepository<User, Long> {
    
}
