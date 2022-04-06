import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CsvParser
 */
public class CsvParser {
	
	/**
	 * Eraikitzaile privatua
	 */
	private CsvParser(){}

	/**
	 * Proiekzioa irakurri
	 * 
	 * @param linea Proiekzioaren csv lerroa
	 */
	public static Proiekzioa irakurriProiekzioa(String linea) {
		String[] valioak = linea.split(";");
		switch (valioak[0]) {
			case "Filma":
				Filma filma = new Filma();
				filma.setId(Integer.parseInt(valioak[1]));
				filma.setIzenburua(valioak[2]);
				filma.setIraupena(Integer.parseInt(valioak[3]));
				filma.setProduktora(valioak[4]);
				filma.setPegi(Integer.parseInt(valioak[5]));
				filma.setGeneroa(valioak[6]);
				return filma;
			case "Dokumentala":
				Dokumentala dokumentala = new Dokumentala();
				dokumentala.setId(Integer.parseInt(valioak[1]));
				dokumentala.setIzenburua(valioak[2]);
				dokumentala.setIraupena(Integer.parseInt(valioak[3]));
				dokumentala.setProduktora(valioak[4]);
				dokumentala.setTema(valioak[5]);
				return dokumentala;
			case "FilmLaburra":
				FilmLaburra filmLaburra = new FilmLaburra();
				filmLaburra.setId(Integer.parseInt(valioak[1]));
				filmLaburra.setIzenburua(valioak[2]);
				filmLaburra.setIraupena(Integer.parseInt(valioak[3]));
				filmLaburra.setFabula(valioak[4]);
				return filmLaburra;
			default:
				return null;
		}
	}

	/**
	 * Proiekzioen zerrenda irakurri
	 * 
	 * @param fitxeroa Proiekzioen zerrenda
	 * @return Proiekzioen zerrenda
	 */
	public static ArrayList<Proiekzioa> irakurriProiekzioenZerrenda(String fitxeroa) {
		File fitx = new File(fitxeroa);
		ArrayList<Proiekzioa> proiekzioenZerrenda = new ArrayList<Proiekzioa>();
		try (Scanner sc = new Scanner(fitx);) {
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				Proiekzioa proiekzioa = irakurriProiekzioa(linea);
				if (proiekzioa != null) {
					proiekzioenZerrenda.add(proiekzioa);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return proiekzioenZerrenda;
	}

	/**
	 * Proiekzioen zerrenda idatzi
	 * 
	 * @param fitxeroa Fitxeroaren izena
	 * @param ArrayList<Proiekzioa> Proiekzioen zerrenda
	 */
	public static void idatziProiekzioenZerrenda(String fitxeroa, ArrayList<Proiekzioa> proiekzioenZerrenda) {
		try {
			File fitx = new File(fitxeroa);
			fitx.createNewFile();
			FileWriter fw = new FileWriter(fitx);
			for (Proiekzioa proiekzioa : proiekzioenZerrenda) {
				fw.write(proiekzioa.toString());
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
