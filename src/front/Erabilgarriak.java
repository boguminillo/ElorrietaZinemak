package front;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextArea;

import back.objektuak.Proiekzioa;

public class Erabilgarriak {

	public static final Map<String, Integer> egunDenborak = Map.ofEntries(
			Map.entry("ASTELEHENA", 5 * 60),
			Map.entry("ASTEARTEA", 5 * 60),
			Map.entry("ASTEAZKENA", 5 * 60),
			Map.entry("OSTEGUNA", 6 * 60),
			Map.entry("OSTIRALA", 6 * 60),
			Map.entry("LARUNBATA", 8 * 60),
			Map.entry("IGANDEA", 8 * 60)
	);

	private Erabilgarriak() {
	}

	public static void egunaAukeratu(ArrayList<Proiekzioa> egunekoProiekzioak, JTextArea txtInfoEgunAutaketa, String eguna) {
		txtInfoEgunAutaketa.setText("");
		int proiekzioak = 0;
		int btbtIraupena = 0;
		int denboraLibre = egunDenborak.get(eguna);
		for (Proiekzioa p : egunekoProiekzioak) {
			proiekzioak++;
			btbtIraupena += p.getIraupena();
			denboraLibre -= p.getIraupena();
		}
		btbtIraupena = btbtIraupena / proiekzioak;
		if (proiekzioak > 0) {
			txtInfoEgunAutaketa.append("Eguneko proiekzioak: " + proiekzioak + "\n");
			txtInfoEgunAutaketa.append("Eguneko proiekzioen batezbesteko iraupena: " + btbtIraupena + "\n");
			txtInfoEgunAutaketa.append("Eguneko denbora librea: " + denboraLibre + "\n");
		} else {
			txtInfoEgunAutaketa.append("Eguneko proiekziorik ez daude\n");
			txtInfoEgunAutaketa.append("Eguneko denbora librea: " + denboraLibre + "\n");
		}
	}

}
