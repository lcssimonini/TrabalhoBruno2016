package br.com.simonini;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.simonini.entities.Brasileiro;
import br.com.simonini.persistence.HumanoDB;

public class Principal {
	private static Scanner scanner = new Scanner(System.in);
	private static List<Brasileiro> brasileiros = new ArrayList<Brasileiro>();
	private static Integer quantidade;

	public static void main(String[] args) throws Exception {
		HumanoDB.loadFromFile();
		
		introducao();
		
		int opcao = 0;
		
		while((opcao = menu(scanner)) != 4) {
			switch (opcao) {
			case 1:
				cadastrar();
				
				break;
				
			case 2:
				printBrasileirosCadastrados();
				break;
				
			case 3:
				printBrasileirosCadastrados();
				break;
				
			case 4:
				sair();
				break;

			default:
				opcaoInvalida();
				break;
			}
		}
	}

	private static void cadastrar() {
		quantidade = getQuantidadeCadastros();
		
		for (int i = 0; i < quantidade; i++) {
			brasileiros.add(Brasileiro.buildBrasileiro(scanner));
		}
	}
	
	private static void opcaoInvalida() {
		System.out.println(" ----------------------------------------");
		System.out.println(" -- Opção inválida, escolha 1,2 3 ou 4  -");
		System.out.println(" ----------------------------------------");
	}
	
	private static Integer menu(Scanner scanner) {
		System.out.println(" ----------------------------------------");
		System.out.println(" -- O que deseja fazer? --  ");
		System.out.println(" -- 1 - Cadastrar  ");
		System.out.println(" -- 2 - Listar usando BubbleSort  ");
		System.out.println(" -- 3 - Listar usando SelectionSort  ");
		System.out.println(" -- 4 - Sair  ");
		System.out.println(" ----------------------------------------");
		
		return scanner.nextInt();
	}
	
	private static void introducao() {
		System.out.println(" ----------------------------------------");
		System.out.println(" -- CADASTRO DE BRASILEIROS --  ");
		System.out.println(" ----------------------------------------");
	}
	
	private static Integer getQuantidadeCadastros() {
		System.out.println(" -- Quantos brasileiros deseja cadastrar? ");
		return scanner.nextInt();
	}
	
	private static void printBrasileirosCadastrados() {
		System.out.println(" ----------------------------------------");
		System.out.println(" -- Brasileiros cadastrados: --  ");
		System.out.println(" ----------------------------------------");
		for (Brasileiro brasileiro : brasileiros) {
			System.out.println(brasileiro.toString());
		}
		System.out.println();
	}
	
	private static void sair() {
		System.out.println(" ----------------------------------------");
		System.out.println(" --  Saindo do programa  --  ");
		System.out.println(" ----------------------------------------");
		System.exit(0);
	}
}
