package com.vodafone.eatwithrandom.enums;

public enum Qeue {
	FACETOFACE("FACETOFACE"),
	GROUP("GROUP");
	
	private final String text;

	Qeue(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}