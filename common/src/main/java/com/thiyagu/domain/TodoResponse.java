package com.thiyagu.domain;

import java.util.List;

import lombok.Data;


@Data
public class TodoResponse {
	
	public enum ResponseSource {
		REMOTE_SERVICE,FALLBACK
	}

	List<Todo> todo;
	
	ResponseSource responseSource;
	
}
