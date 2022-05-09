package front;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import back.objektuak.*;

public class Erabilgarriak {

	public static final Map<String, Integer> egunDenborak = Map.ofEntries(
			Map.entry("ASTELEHENA", 5 * 60),
			Map.entry("ASTEARTEA", 5 * 60),
			Map.entry("ASTEAZKENA", 5 * 60),
			Map.entry("OSTEGUNA", 6 * 60),
			Map.entry("OSTIRALA", 6 * 60),
			Map.entry("LARUNBATA", 8 * 60),
			Map.entry("IGANDEA", 8 * 60));

	public static String FILMA = "Filma";
	public static String DOKUMENTALA = "Dokumentala";
	public static String FILMLABURRA = "FilmLaburra";

	private Erabilgarriak() {
	}

	public static void egunaAukeratu(ArrayList<Proiekzioa> egunekoProiekzioak, JTextArea txtInfoEgunAutaketa,
			String eguna) {
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

	public static void tablaEdukia(DefaultTableModel tableModel, ArrayList<Proiekzioa> proiekzioak, String mota) {
		for (Proiekzioa proiekzioa : proiekzioak) {
			if (mota.equals(FILMA)) {
				if (proiekzioa instanceof Filma) {
					Filma filma = (Filma) proiekzioa;
					tableModel.addRow(new Object[] {
							filma.getId(),
							filma.getIzenburua(),
							filma.getIraupena(),
							filma.getPegi(),
							filma.getGeneroa()
					});
				}
			} else if (mota.equals(DOKUMENTALA)) {
				if (proiekzioa instanceof Dokumentala) {
					Dokumentala dokumental = (Dokumentala) proiekzioa;
					tableModel.addRow(new Object[] {
							dokumental.getId(),
							dokumental.getIzenburua(),
							dokumental.getIraupena(),
							dokumental.getTema()
					});
				}
			} else {
				if (proiekzioa instanceof FilmLaburra) {
					FilmLaburra filmLaburra = (FilmLaburra) proiekzioa;
					tableModel.addRow(new Object[] {
							filmLaburra.getId(),
							filmLaburra.getIzenburua(),
							filmLaburra.getIraupena(),
							filmLaburra.getFabula()
					});
				}
			}
		}
	}

}
