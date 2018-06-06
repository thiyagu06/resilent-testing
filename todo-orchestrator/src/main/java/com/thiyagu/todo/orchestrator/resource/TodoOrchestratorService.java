package com.thiyagu.todo.orchestrator.resource;

import java.util.ArrayList;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.thiyagu.domain.TodoResponse;
import com.thiyagu.domain.TodoResponse.ResponseSource;
import com.thiyagu.todo.hystrix.command.GetTodosCommand;

@Service
public class TodoOrchestratorService {

	@Autowired
	private RestTemplate restTemplate;

	public TodoResponse callTodoService() throws Exception {
		Future<TodoResponse> future = new GetTodosCommand(HystrixCommandGroupKey.Factory.asKey("hotdeals"), 200,
				restTemplate).queue();
		return future.get();
	}

	public TodoResponse todoOrchestrationFallBack() {
		TodoResponse res = new TodoResponse();
		res.setTodo(new ArrayList<>());
		res.setResponseSource(ResponseSource.FALLBACK);
		return res;
	}

}
