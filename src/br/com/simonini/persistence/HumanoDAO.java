package br.com.simonini.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.simonini.entities.Brasileiro;

public class HumanoDAO {
	private static final String HUMANOS_FILE_PATH = "./humanos.file";
	private static Path pathToSave = Paths.get(HUMANOS_FILE_PATH);
	
	public static void saveToFile(Brasileiro humano) {
		Gson gson = new Gson();
		String brasileiroJson = gson.toJson(humano);
		String lineToSave = brasileiroJson+System.lineSeparator();
		try {
			Files.write(pathToSave, lineToSave.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Brasileiro> readFromFile() throws Exception {
		List<Brasileiro> brasileiros = new ArrayList<Brasileiro>(); 
		List<String> linhasArquivo = new ArrayList<String>();
		
		Scanner s = new Scanner(new File(HUMANOS_FILE_PATH));
		while (s.hasNext()){
			linhasArquivo.add(s.next());
		}
		s.close();
		
		Gson gson = new Gson();
		for (String braString : linhasArquivo) {
			brasileiros.add(gson.fromJson(braString, Brasileiro.class));
		}
		return brasileiros;
	}
	
	public static Brasileiro readHumano(String linha) {
		Brasileiro humano = null;		
		Gson gson = new Gson();		
		humano = gson.fromJson(linha, Brasileiro.class);
		return humano;
	}
	
	public static void main(String[] args) throws Exception {
		Brasileiro b1 = new Brasileiro(10, 10, true, "lucas", "321321", 30);
		Brasileiro b2 = new Brasileiro(10, 10, true, "mariazinha", "321321", 30);
		Brasileiro b3 = new Brasileiro(10, 10, true, "bruno", "321321", 30);
		Brasileiro b4 = new Brasileiro(10, 10, true, "ana", "321321", 30);
		Brasileiro b5 = new Brasileiro(10, 10, true, "carolina", "321321", 30);
		
		HumanoDAO.saveToFile(b1);
		HumanoDAO.saveToFile(b2);
		HumanoDAO.saveToFile(b3);
		HumanoDAO.saveToFile(b4);
		HumanoDAO.saveToFile(b5);
		
		HumanoDB.loadFromFile();
		
		for (Brasileiro b : HumanoDB.ordenaPorNome()) {
			System.out.println(b.getName());
		}
	}
}
