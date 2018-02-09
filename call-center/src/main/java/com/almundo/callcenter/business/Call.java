package com.almundo.callcenter.business;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * The Class Call is a Runnable that simulates any call.
 */
public class Call implements Runnable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(Call.class);

	/** The Constant MIN_TIME. */
	public static final Integer MIN_TIME = 5000;

	/** The Constant MAX_TIME. */
	public static final Integer MAX_TIME = 10000;

	/** The number. */
	private String number;

	/** The duration. */
	private Integer duration;

	/**
	 * Instantiates a new call.
	 *
	 * @param number the number
	 */
	public Call(final String number) {
		this.number = number;
		this.duration = generateRandomTime();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			LOGGER.info(String.format("Call for %s has been started", this.number));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			LOGGER.warn(e.getMessage());
		}
		LOGGER.info(String.format("Call for %s has finished in %s seconds", this.number, this.duration));

	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * Generate random time.
	 *
	 * @return the integer
	 */
	private Integer generateRandomTime() {
		final Random random = new Random();
		final Integer baseTime = MAX_TIME - MIN_TIME;
		return random.nextInt(baseTime) + MIN_TIME;
	}

}
