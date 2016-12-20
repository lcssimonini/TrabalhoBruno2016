package br.com.simonini.entities;

public abstract class Humano implements Comparable<Brasileiro> {
	
	private float mass;
	private float heigth;
	private boolean isAlive;
	
	
	public Humano(float mass, float heigth, Boolean isAlive) {
		super();
		this.mass = mass;
		this.heigth = heigth;
		this.isAlive = isAlive;
	}

	public Float getMass() {
		return mass;
	}
	
	public void setMass(float mass) {
		this.mass = mass;
	}
	
	public Float getHeigth() {
		return heigth;
	}
	
	public void setHeigth(float heigth) {
		this.heigth = heigth;
	}
	
	public Boolean getIsAlive() {
		return isAlive;
	}
	
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
