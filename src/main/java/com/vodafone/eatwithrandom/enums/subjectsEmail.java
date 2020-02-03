package com.vodafone.eatwithrandom.enums;

public enum subjectsEmail {
	SIGNUP("Confirmación de registro"),
	ASSIGNTABLE("Mesa asignada"),
	RECOVERYPASSWORD("Reseteo de contraseña");
	
	private final String text;

	subjectsEmail(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}