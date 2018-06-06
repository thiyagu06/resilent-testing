package com.thiyagu.disruptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The class to represent the setting values to be used in disruptor service
 * 
 * @author Thiyagu
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class DistruptorSettings {

	/**
	 * Setting to enable whether to disrupt the rest service
	 */
	@Value("${app.distrupt.enabled:false}")
	private boolean enabled;

	/**
	 * Setting to enable whether to disrupt the service at rest resource level
	 */
	@Value("${app.distrupt.api:false}")
	private boolean distruptApi;

	/**
	 *  Setting to induce the latency disruptor at the rest resource level 
	 */
	@Value("${app.distrupt.api.latency:false}")
	private boolean disruptApiLatecny;

	/**
	 *  Setting for latency disruptor start range
	 * 
	 */
	@Value("${app.distrupt.api.latency.startRange:1000}")
	private int distruptApiLatencyStart;

	/**
	 * Setting for latency disruptor end range
	 */
	@Value("${app.distrupt.api.latency.endRange:3000}")
	private int distruptApiLatencyEnd;

	/**
	 * Setting to induce the exception disruptor at the rest resource level
	 */
	@Value("${app.distrupt.api.execption:false}")
	private boolean disruptApiException;

}
