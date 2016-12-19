package br.com.simonini.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.simonini.entities.Brasileiro;
import br.com.simonini.entities.Humano;

public class HumanoDB {
	
	private static Integer key = new Integer(0);
	private static Map<Integer, Humano> humanosSalvos = new HashMap<Integer, Humano>();
	
	public static void loadFromFile() throws Exception {
		List<Brasileiro> humanosLidos = HumanoDAO.readFromFile();
		
		for (Brasileiro brasileiro : humanosLidos) {
			humanosSalvos.put(key++,brasileiro);
		}
	}
	
	public static Humano getHumano(Integer id) {
		return humanosSalvos.get(id);
	}
	
	public static void setHumano() {
		
	}

}
