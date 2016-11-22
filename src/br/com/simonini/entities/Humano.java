package br.com.simonini.entities;

public abstract class Humano {
	
	private Float mass;
	private Float heigth;
	private Boolean isAlive;
	
	
	public Humano(Float mass, Float heigth, Boolean isAlive) {
		super();
		this.mass = mass;
		this.heigth = heigth;
		this.isAlive = isAlive;
	}

	public Float getMass() {
		return mass;
	}
	
	public void setMass(Float mass) {
		this.mass = mass;
	}
	
	public Float getHeigth() {
		return heigth;
	}
	
	public void setHeigth(Float heigth) {
		this.heigth = heigth;
	}
	
	public Boolean getIsAlive() {
		return isAlive;
	}
	
	public void setIsAlive(Boolean isAlive) {
		this.isAlive = isAlive;
	}
}
