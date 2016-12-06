package br.com.simonini.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

import br.com.simonini.entities.Brasileiro;
import br.com.simonini.entities.Humano;

public class HumanoDAO {
	private static final String HUMANOS_FILE = "humanos.file";
	private static Path pathToSave;
	
	public HumanoDAO() {
		HumanoDAO.pathToSave = Paths.get(HUMANOS_FILE);
	}
	
	public static void saveToFile(Humano humano) {
		Gson gson = new Gson();
		String humanoJson = gson.toJson(humano);
		String lineToSave = humanoJson+System.lineSeparator();
		try {
			Files.write(pathToSave, lineToSave.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Brasileiro brasileiro = new Brasileiro(10, 10, true, "lucas", "321321", 30);
	}

}
