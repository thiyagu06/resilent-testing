package com.thiyagu.todo.orchestrator.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiyagu.domain.TodoResponse;

@RestController
@RequestMapping("/todos")
public class TodoOrchestratorResource {

	@Autowired
	private TodoOrchestratorService todoOrchestratorService;

	@GetMapping
	public TodoResponse getAllTodos() throws Exception {
		return todoOrchestratorService.callTodoService();
	}
}
