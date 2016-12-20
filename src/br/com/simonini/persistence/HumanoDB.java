package br.com.simonini.persistence;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.simonini.entities.Brasileiro;
import br.com.simonini.entities.Humano;

public class HumanoDB {
	
	private static Integer key = new Integer(0);
	private static Map<Integer, Brasileiro> brasileirosSalvos = new HashMap<Integer, Brasileiro>();
	
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
		List<Brasileiro> brasileirosLidos = HumanoDAO.readFromFile();
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

}
