package com.vodafone.eatwithrandom.enums;

public enum Actions {
	UP("UP"),
	DOWN("DOWN"),
	UPDATEPROFILE("updateprofile"),
	UPDATEPASSWORD("updatepassword");
	
	private final String text;

	Actions(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}