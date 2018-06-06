package com.thiyagu.disruptor;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implement the logic for disrupt the service
 * 
 * @author thiyagu
 *
 */
@Component
public class DisruptorService {

	/**
	 * The settings to control how frequent the service needs to be disrupt.
	 */
	public static final int FREQUENCY = 5;

	/**
	 * The disruptor settings
	 */
	@Autowired
	private DistruptorSettings distruptorSettings;

	/**
	 * The class to implement the disrupt logic
	 */
	public void disrupt() {

		if (isEnabled() && isDisrupt()) {
			if (distruptorSettings.isDisruptApiException() && distruptorSettings.isDisruptApiLatecny()) {
				int exceptionRandom = getExceptionRandom();
				// either to disrupt the latency of the method or throw exception
				if (exceptionRandom < 7) {
					generateLatency();
				} else {
					generateDisruptException();
				}
			}
		}

	}

	/**
	 *  throws runtime exception to disrupt the current execution
	 */
	private void generateDisruptException() {
		throw new RuntimeException("Disruptor Exception");
	}

	/**
	 * Make the current thread to sleep based on the latency range settings
	 */
	private void generateLatency() {
		int timeOut = RandomUtils.nextInt(distruptorSettings.getDistruptApiLatencyStart(),
				distruptorSettings.getDistruptApiLatencyEnd());
		try {
			Thread.sleep(timeOut);
		} catch (Exception e) {
			// swallow it
		}

	}

	/**
	 * Checks whether the rest service execution has to be disrupt or not
	 * 
	 * @return true/false
	 */
	private boolean isEnabled() {
		return distruptorSettings.isEnabled();
	}

	/**
	 * Checks whether the current execution can be disrupt or not
	 * 
	 * @return true/false
	 */
	private boolean isDisrupt() {
		return (RandomUtils.nextInt(1, 11) >= FREQUENCY);
	}

	/**
	 * Generates random integer
	 * 
	 * @return
	 */
	private int getExceptionRandom() {
		return RandomUtils.nextInt(0, 10);
	}
}
