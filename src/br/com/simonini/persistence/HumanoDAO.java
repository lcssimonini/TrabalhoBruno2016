package br.com.simonini.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;

import br.com.simonini.entities.Brasileiro;

public class HumanoDAO {
	private static Path pathToSave = Paths.get("./humanos.file");
	
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
		Stream<String> linhasArquivo = Files.lines(pathToSave);
		Gson gson = new Gson();
		linhasArquivo.map(streamLine -> brasileiros.add(gson.fromJson(streamLine, Brasileiro.class)));
		linhasArquivo.close();
		return brasileiros;
	}
	
	public static Brasileiro readHumano(String linha) {
		Brasileiro humano = null;		
		Gson gson = new Gson();		
		humano = gson.fromJson(linha, Brasileiro.class);
		return humano;
	}
	
	public static void main(String[] args) throws IOException {
		Brasileiro b1 = new Brasileiro(10, 10, true, "lucas", "321321", 30);
		Brasileiro b2 = new Brasileiro(10, 10, true, "mariazinha", "321321", 30);
		
		HumanoDAO.saveToFile(b1);
		HumanoDAO.saveToFile(b2);
	}

}
