import java.util.ArrayList;

public class Prueba {
	
	public static void main(String[] args) {
		ArrayList<Proiekzioa> lista = CsvParser.irakurriProiekzioenZerrenda("datuak/films.txt");
		for (Proiekzioa proiekzioa : lista) {
			System.out.print(proiekzioa);
		}

		CsvParser.idatziProiekzioenZerrenda("datuak/prueba.csv", lista);

	}
}
