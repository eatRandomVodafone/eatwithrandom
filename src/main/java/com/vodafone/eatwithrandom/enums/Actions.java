package com.vodafone.eatwithrandom.enums;

public enum Actions {
	UP("UP"),
	DOWN("DOWN");
	
	private final String text;

	Actions(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}