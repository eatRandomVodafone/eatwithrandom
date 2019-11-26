package com.vodafone.eatwithrandom.enums;

public enum Status {
	STANDBY("STANDBY"),
	WAITING("WAITING"),
	ASSIGNED("ASSIGNED");
	
	private final String text;

	Status(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}