/**
 * 
 */
package com.thiyagu.todoservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface all CRUD operation of todo entity
 * 
 * @author Thiyagu
 *
 */
@Repository	
public interface TodoRepository extends JpaRepository<com.thiyagu.todoservice.entity.Todo, String> {

}
