/**
 * 
 */
package com.thiyagu.todoservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiyagu.domain.TodoStatus;
import com.thiyagu.todoservice.entity.Todo;
import com.thiyagu.todoservice.exception.TodoException;
import com.thiyagu.todoservice.repo.TodoRepository;

/**
 * Service implementation for todo.
 * 
 * @author Thiyagu
 *
 */
@Service
public class TodoServiceImpl implements TodoService {

	/**
	 * instance of todoRepository 
	 */
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<Todo> findTodos() {
		return todoRepository.findAll();
	}

	@Override
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Todo markCompleted(String todoId) throws TodoException {
		Todo todoTobeCompleted = todoRepository.getOne(todoId);
		if (todoTobeCompleted != null) {
			todoTobeCompleted.setStatus(TodoStatus.COMPLETED);
			return todoRepository.save(todoTobeCompleted);
		}
		throw new TodoException(101, "Todo not exisits for id"+ todoId);

	}

	@Override
	public void deleteTodo(String todoId) {
		if (todoRepository.existsById(todoId)) {
			todoRepository.deleteById(todoId);
		}
		throw new TodoException(102, "Todo not exisits for id"+ todoId);
	}

}
