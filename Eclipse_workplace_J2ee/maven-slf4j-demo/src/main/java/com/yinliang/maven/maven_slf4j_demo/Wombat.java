package com.yinliang.maven.maven_slf4j_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wombat {
	final Logger logger = LoggerFactory.getLogger(Wombat.class);
	Integer t;
	Integer oldT;

	public void setTemperature(Integer temperature) {
		this.oldT = this.t;
		this.t = temperature;
		this.logger.error(" Temperature set to {}. Old temperature was {}. ", this.t, this.oldT);
		if (temperature.intValue() > 50) {
			this.logger.info(" Temperature has risen above 50 degrees. ");
		}
	}

	public static void main(String[] args) {
		Wombat wombat = new Wombat();
		wombat.setTemperature(1);
		wombat.setTemperature(55);
	}
}