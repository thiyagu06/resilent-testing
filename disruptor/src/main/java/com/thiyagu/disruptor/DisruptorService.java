package com.thiyagu.disruptor;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisruptorService {

	@Autowired
	private DistruptorSettings distruptorSettings;

	public void disrupt() {

		if (isEnabled() && isDisrupt() ) {
			if (distruptorSettings.isDisruptApiException() && distruptorSettings.isDisruptApiLatecny()) {
				int exceptionRandom = getExceptionRandom();
				if (exceptionRandom < 7) {
					generateLatency();
				} else {
					generateDisruptException();
				}
			}
		}

	}

	private void generateDisruptException() {
		throw new RuntimeException("Disruptor Exception");
	}

	private void generateLatency() {
		int timeOut = RandomUtils.nextInt(distruptorSettings.getDistruptApiLatencyStart(),distruptorSettings.getDistruptApiLatencyEnd());
		try {
			Thread.sleep(timeOut);
		} catch (Exception e) {
			// swallow it
		}

	}

	private boolean isEnabled() {
		return distruptorSettings.isEnabled();
	}
	
	private boolean isDisrupt() {
		   return (RandomUtils.nextInt(1, 11) >= 5);
	}

	private int getExceptionRandom() {
		return RandomUtils.nextInt(0,10);
	}
}
