
package com.vodafone.eatwithrandom.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Document(collection = "config")
@JsonPropertyOrder({"numeroNext", "minPersonasGrupo", "mesas"})

public class Config {

	@NotNull    
    private int numeroNext;
    @NotNull    
    private int minPersonasGrupo;
    @NotNull    
    private Mesa[] mesas;

	public Config(int numeroNext, int minPersonasGrupo, Mesa[] mesas) {
		this.numeroNext = numeroNext;
		this.minPersonasGrupo = minPersonasGrupo;
		this.mesas = mesas;
	}

	public int getNumeroNext() {
		return numeroNext;
	}
	public void setNumeroNext(int numeroNext) {
		this.numeroNext = numeroNext;
	}
	public int getMinPersonasGrupo() {
		return minPersonasGrupo;
	}
	public void setMinPersonasGrupo(int minPersonasGrupo) {
		this.minPersonasGrupo = minPersonasGrupo;
	}
	public Mesa[] getMesas() {
		return mesas;
	}
	public void setMesas(Mesa[] mesas) {
		this.mesas = mesas;
	}
    
}
