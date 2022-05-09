package front;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import back.CsvParser;
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

	public static String egunaAukeratu(ArrayList<Proiekzioa> egunekoProiekzioak, String eguna) {
		String laburpena = "";
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
			laburpena = laburpena.concat("Eguneko proiekzioak: " + proiekzioak + "\n");
			laburpena = laburpena.concat("Eguneko proiekzioen batezbesteko iraupena: " + btbtIraupena + "\n");
			laburpena = laburpena.concat("Eguneko denbora librea: " + denboraLibre + "\n");
		} else {
			laburpena = laburpena.concat("Eguneko proiekziorik ez daude\n");
			laburpena = laburpena.concat("Eguneko denbora librea: " + denboraLibre + "\n");
		}
		return laburpena;
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

	public static ArrayList<Proiekzioa> taulaToArrayList(DefaultTableModel modelo, String mota) {
		ArrayList<Proiekzioa> proiekzioak = new ArrayList<>();
		for (int i = 0; i < modelo.getRowCount(); i++) {
			int id = (Integer) modelo.getValueAt(i, 0);
			String izenburua = (String) modelo.getValueAt(i, 1);
			int iraupena = (Integer) modelo.getValueAt(i, 2);
			if (mota.equals(FILMA)) {
				String produktora = (String) modelo.getValueAt(i, 3);
				int pegi = (Integer) modelo.getValueAt(i, 4);
				String generoa = (String) modelo.getValueAt(i, 5);
				proiekzioak.add(new Filma(id, izenburua, iraupena, produktora, pegi, generoa));
			} else if (mota.equals(DOKUMENTALA)) {
				String produktora = (String) modelo.getValueAt(i, 3);
				String tema = (String) modelo.getValueAt(i, 4);
				proiekzioak.add(new Dokumentala(id, izenburua, iraupena, produktora, tema));
			} else {
				String fabula = (String) modelo.getValueAt(i, 3);
				proiekzioak.add(new FilmLaburra(id, izenburua, iraupena, fabula));
			}
		}
		return proiekzioak;
	}
}
