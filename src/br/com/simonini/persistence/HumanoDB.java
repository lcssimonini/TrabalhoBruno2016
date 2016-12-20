package br.com.simonini.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import br.com.simonini.entities.Brasileiro;
import br.com.simonini.entities.Humano;

public class HumanoDB {
	private static final String HUMANOS_FILE_PATH = "./humanos.file";
	private static Path pathToSave = Paths.get(HUMANOS_FILE_PATH);
	private static Integer key = new Integer(0);
	private static Map<Integer, Brasileiro> brasileirosSalvos = new HashMap<Integer, Brasileiro>();
	
	public static void alterarRegistro(String nome) throws Exception {
		if (StringUtils.isEmpty(nome)) {
			throw new Exception("nome não pode estar vazio");
		}
		
		Brasileiro brasileiroAlterado = null;
		Integer idToUpdate = null;
		
		for (Integer indice : brasileirosSalvos.keySet()) {
			brasileiroAlterado = brasileirosSalvos.get(indice);
			if (nome.equals(brasileiroAlterado.getName())) {
				idToUpdate = indice;
				break;
			}
		}
		
		if (brasileiroAlterado == null) {
			throw new Exception("nome informado não foi cadastrado no sistema");
		}
		
		Brasileiro.buildBrasileiro(brasileiroAlterado);
		brasileirosSalvos.put(idToUpdate, brasileiroAlterado);		
	}
	
	public static void removeByNome(String nome) throws Exception {
		if (StringUtils.isEmpty(nome)) {
			throw new Exception("nome não pode estar vazio");
		}
		
		Brasileiro brasileiroRemovido = null;
		Integer idToRemove = null;
		
		for (Integer indice : brasileirosSalvos.keySet()) {
			brasileiroRemovido = brasileirosSalvos.get(indice);
			if (nome.equals(brasileiroRemovido.getName())) {
				idToRemove = indice;
				break;
			}
		}
		
		brasileirosSalvos.remove(idToRemove);
		
		System.out.println("removido: "+brasileiroRemovido);
	}
	
	public static void salvaTodos() throws Exception {
		limpaArquivo();
		for (Brasileiro brasileiro : brasileirosSalvos.values()) {
			saveToFile(brasileiro);			
		}
	}
	
	private static void limpaArquivo() throws Exception {
		PrintWriter writer = new PrintWriter(new File(HUMANOS_FILE_PATH));
		writer.print("");
		writer.close();
	}
	
	public static List<Brasileiro> ordenaPorNome() {
		Collection<Brasileiro> brasileirosSemOrdem = brasileirosSalvos.values();
		
		Brasileiro[] braArray = brasileirosSemOrdem.toArray(new Brasileiro[brasileirosSemOrdem.size()]);
		Brasileiro aux = null;
		
		
		for (int i = braArray.length; i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                if (braArray[j - 1].compareTo(braArray[j]) > 0) {
                    aux = braArray[j];
                    braArray[j] = braArray[j - 1];
                    braArray[j - 1] = aux;
                }
            }
        }
		
		return Arrays.asList(braArray);
	}
	
	public static List<Brasileiro> ordenaPorIdade() {
		Collection<Brasileiro> brasileirosSemOrdem = brasileirosSalvos.values();
		
		Brasileiro[] braArray = brasileirosSemOrdem.toArray(new Brasileiro[brasileirosSemOrdem.size()]);
		Brasileiro aux = null;
		
		
		for (int i = braArray.length; i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                if (braArray[j - 1].getAge().compareTo(braArray[j].getAge()) > 0) {
                    aux = braArray[j];
                    braArray[j] = braArray[j - 1];
                    braArray[j - 1] = aux;
                }
            }
        }
		
		return Arrays.asList(braArray);
	}
	
	public static Map<Integer, Brasileiro> getBrasileirosSalvos() {
		return brasileirosSalvos;
	}
	
	public static void loadFromFile() throws Exception {
		List<Brasileiro> brasileirosLidos = readFromFile();
		for (Brasileiro brasileiro : brasileirosLidos) {
			brasileirosSalvos.put(getNextKey(), brasileiro);
		}
		
		System.out.println("humanos lidos: " + brasileirosSalvos.keySet().size());
	}
	
	public static Humano getHumano(Integer id) {
		return brasileirosSalvos.get(id);
	}
	
	public static void putBrasileiro(Brasileiro brasileiro) {
		brasileirosSalvos.put(getNextKey(), brasileiro);
	}
	
	private static Integer getNextKey() {
		return key++;
	}
	
	public static void saveToFile(Brasileiro brasileiro) {
		Gson gson = new Gson();
		String brasileiroJson = gson.toJson(brasileiro);
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
}
