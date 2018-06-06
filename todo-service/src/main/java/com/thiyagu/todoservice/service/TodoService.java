/**
 * 
 */
package com.thiyagu.todoservice.service;

import java.util.List;

import com.thiyagu.todoservice.entity.Todo;
import com.thiyagu.todoservice.exception.TodoException;


/**
 * Basic interface for Todo Service
 * @author Thiyagu
 *
 */
public interface TodoService {

	/**
	 * @return list of todos
	 */
	List<Todo> findTodos();

	/**
	 * Save the give todo in Database
	 * 
	 * @param todo
	 * @return
	 */
	Todo saveTodo(Todo todo);

	/**
	 * Todo Id to be mark as Completed.If given todoId not exists throws exception.
	 * 
	 * @param todoId
	 * @return completed todo
	 * @throws TodoException
	 */
	Todo markCompleted(String todoId) throws TodoException;

	/**
	 * Todo Id to be deleted.If given todoId not exists throws exception.
	 * 
	 * @param todoId
	 * @throws TodoException
	 */
	void deleteTodo(String todoId) throws TodoException;
}
