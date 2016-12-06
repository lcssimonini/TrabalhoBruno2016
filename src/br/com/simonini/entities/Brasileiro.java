package br.com.simonini.entities;

import java.util.Scanner;

public class Brasileiro extends Humano {

	private String name;
	private String cpf;
	private int age;

	
	public Brasileiro(float mass, float heigth, Boolean isAlive, String name, String cpf, Integer age) {
		super(mass, heigth, isAlive);
		this.name = name;
		this.cpf = cpf;
		this.age = age;
	}

	public static Brasileiro buildBrasileiro(Scanner scanner) {
		
		float mass;
		float heigth;
		String cpf;
		int age;
		String name;
		
		System.out.println(" -- Entre com os dados do brasileiro que deseja cadastrar: ");
		System.out.println();
		
		System.out.println(" -- nome: ");
		name = scanner.nextLine();
		
		System.out.println(" -- idade: ");
		age = scanner.nextInt();

		System.out.println(" -- Altura: ");
		heigth = scanner.nextFloat();
		
		System.out.println(" -- Massa: ");
		mass = scanner.nextFloat();
		
		System.out.println(" -- CPF: ");
		cpf = scanner.next();
		
		Brasileiro brasileiro = new Brasileiro(mass, heigth, true, name, cpf, age);
		
		return brasileiro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		name = nome;
	}
	
	@Override
	public String toString() {
		return "Brasileiro [Name=" + name + ", cpf=" + cpf + ", age=" + age + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brasileiro other = (Brasileiro) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
