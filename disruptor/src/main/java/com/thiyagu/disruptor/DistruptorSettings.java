package com.thiyagu.disruptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class DistruptorSettings {

	@Value("${app.distrupt.enabled:false}")
	private boolean enabled;

	@Value("${app.distrupt.api:false}")
	private boolean distruptApi;
	
	@Value("${app.distrupt.api.latency:false}")
	private boolean disruptApiLatecny;

	@Value("${app.distrupt.api.latency.startRange:1000}")
	private int distruptApiLatencyStart;

	@Value("${app.distrupt.api.latency.endRange:3000}")
	private int distruptApiLatencyEnd;

	@Value("${app.distrupt.api.execption:false}")
	private boolean disruptApiException;

}
