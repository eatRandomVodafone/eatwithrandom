package com.vodafone.eatwithrandom.enums;

public enum Queue {
	FACETOFACE("FACETOFACE"),
	GROUP("GROUP");
	
	private final String text;

	Queue(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}