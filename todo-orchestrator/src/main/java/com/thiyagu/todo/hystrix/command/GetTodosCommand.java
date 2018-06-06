package com.thiyagu.todo.hystrix.command;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.thiyagu.domain.Todo;
import com.thiyagu.domain.TodoResponse;
import com.thiyagu.domain.TodoResponse.ResponseSource;

public class GetTodosCommand extends HystrixCommand<TodoResponse> {

	private RestTemplate restTemplate;

	public GetTodosCommand(HystrixCommandGroupKey group, int timeout, RestTemplate restTemplate) {
		super(group, timeout);
		this.restTemplate = restTemplate;

	}

	@Override
	protected TodoResponse run() throws Exception {
		List<Todo> response = restTemplate.exchange("http://localhost:8085/todo/", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Todo>>() {
				}).getBody();
		TodoResponse res = new TodoResponse();
		res.setTodo(response);
		res.setResponseSource(ResponseSource.REMOTE_SERVICE);
		return res;
	}

	@Override
	protected TodoResponse getFallback() {
		TodoResponse res = new TodoResponse();
		res.setTodo(Collections.emptyList());
		res.setResponseSource(ResponseSource.FALLBACK);
		return res;
	}

}
