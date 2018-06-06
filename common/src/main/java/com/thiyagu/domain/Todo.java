/**
 * 
 */
package com.thiyagu.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * The todo Entity
 * 
 * @author Thiyagu
 *
 */
@Data
@ToString
@JsonPropertyOrder({ "id", "name", "status" })
@RequiredArgsConstructor
@NoArgsConstructor
public class Todo implements Serializable {

	/**
	 * Default Serialization Id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The unique ID of todo
	 */
	@JsonProperty("id")
	private String id;

	/**
	 * The description of todo item
	 */
	@JsonProperty("description")
	@NonNull
	private String description;

	/**
	 * The property to decide todo is done or not
	 */
	@JsonProperty("status")
	private TodoStatus status = TodoStatus.PENDING;
}
