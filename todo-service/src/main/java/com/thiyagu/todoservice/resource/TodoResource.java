package com.thiyagu.todoservice.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiyagu.domain.Todo;
import com.thiyagu.todoservice.exception.TodoException;
import com.thiyagu.todoservice.service.TodoService;

/**
 * The resource class for todo api
 * 
 * @author Thiyagu
 *
 */
@RestController
@RequestMapping("/todo")
public class TodoResource {

	/**
	 * The instance of todo service
	 */
	@Autowired
	private TodoService todoService;

	/**
	 * The todo ID error message constant
	 */
	private static final String TODO_ID_ERROR_MESSAGE = "todo ID cannot be null";

	/**
	 * API for creating todo.
	 * 
	 * @param todo
	 * @return the newly created todo
	 */
	@PostMapping
	public ResponseEntity<Todo> createTodo(@RequestBody @Valid Todo todo) {
		com.thiyagu.todoservice.entity.Todo entity = new com.thiyagu.todoservice.entity.Todo();
		entity.setDescription(todo.getDescription());
		entity.setStatus(todo.getStatus());
		com.thiyagu.todoservice.entity.Todo createdEntity = todoService.saveTodo(entity);
		todo.setId(createdEntity.getId());
		return new ResponseEntity<>(todo, HttpStatus.CREATED);
	}

	/**
	 * Api for deleting todo by given todo id
	 * 
	 * @param todoId
	 * @return empty response body
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTodo(
			@PathVariable("id") @NotEmpty(message = TODO_ID_ERROR_MESSAGE) String todoId) {
		todoService.deleteTodo(todoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Api for all to return all todos
	 * 
	 * @return list of todo object
	 */
	@GetMapping
	public ResponseEntity<List<Todo>> findAllTodos() {
		List<com.thiyagu.todoservice.entity.Todo> entities = todoService.findTodos();
		if (CollectionUtils.isEmpty(entities)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<Todo> todos = new ArrayList<>();
		for(com.thiyagu.todoservice.entity.Todo entity : entities) {
			todos.add(toDomain(entity));
		}
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}

	/**
	 * Api for mark the todo status as completed by todo id.
	 * 
	 * @param todoId
	 * @return updated todo object
	 * @throws TodoException
	 */
	@PutMapping("/markCompleted/{id}")
	public ResponseEntity<Todo> markAsCompleted(
			@PathVariable("id") @NotEmpty(message = TODO_ID_ERROR_MESSAGE) String todoId) throws TodoException {
		com.thiyagu.todoservice.entity.Todo completedTodo = todoService.markCompleted(todoId);
		Todo todo = toDomain(completedTodo);
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}

	private Todo toDomain(com.thiyagu.todoservice.entity.Todo completedTodo) {
		Todo todo = new Todo();
		todo.setId(completedTodo.getId());
		todo.setDescription(completedTodo.getDescription());
		todo.setStatus(completedTodo.getStatus());
		return todo;
	}
}
