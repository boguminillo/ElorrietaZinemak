import java.io.File;
import java.util.ArrayList;

public class Prueba {
	
	public static void main(String[] args) {
		File fichero = new File("datuak/films.txt");
		ArrayList<Proiekzioa> lista = CsvParser.irakurriProiekzioenZerrenda(fichero);
		for (Proiekzioa proiekzioa : lista) {
			System.out.println(proiekzioa);
		}
		System.out.println("\n\n");
	}
}
