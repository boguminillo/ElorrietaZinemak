package front;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import back.objektuak.Dokumentala;
import back.objektuak.FilmLaburra;
import back.objektuak.Filma;
import back.objektuak.Proiekzioa;

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
		// documentar error al cancelar los cambios se duplicaba en lugar de reiniciar las tablas, solucionado con la linea de debajo`
		tableModel.setRowCount(0);
		for (Proiekzioa proiekzioa : proiekzioak) {
			if (mota.equals(FILMA)) {
				if (proiekzioa instanceof Filma) {
					Filma filma = (Filma) proiekzioa;
					tableModel.addRow(new Object[] {
							filma.getId(),
							filma.getIzenburua(),
							filma.getIraupena(),
							filma.getProduktora(),
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
							dokumental.getProduktora(),
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
			int iraupena;
			// documentar error cuando estaba en blanco, solucionado con los try catch
			try{iraupena = (Integer) modelo.getValueAt(i, 2);} catch (Exception e) {iraupena = 0;}
			if (mota.equals(FILMA)) {
				String produktora = (String) modelo.getValueAt(i, 3);
				int pegi;
				try { pegi = (Integer) modelo.getValueAt(i, 4);} catch (Exception e) {pegi = 0;}
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

	public static String egunLaburpena(ArrayList<Proiekzioa> egunekoProiekzioak, String eguna) {
		String laburpena = "";
		int denboraLibre = egunDenborak.get(eguna);
		for (Proiekzioa p : egunekoProiekzioak) {
			laburpena = laburpena.concat(p.toLaburpenTestua() + "\n");
			denboraLibre -= p.getIraupena();
		}
		laburpena = laburpena.concat("Eguneko denbora librea: " + denboraLibre);
		return laburpena;
	}

	public static Proiekzioa proiekzioaLortu(int id, ArrayList<Proiekzioa> proiekzioak) {
		for (Proiekzioa p : proiekzioak) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	public static int denboraLibre(ArrayList<Proiekzioa> proiekzioak, String eguna) {
		int denboraLibre = egunDenborak.get(eguna);
		for (Proiekzioa p : proiekzioak) {
			denboraLibre -= p.getIraupena();
		}
		return denboraLibre;
	}

	public static boolean generoaBadago(String generoa, ArrayList<Proiekzioa> egunekoProiekzioak) {
		for (Proiekzioa p : egunekoProiekzioak) {
			if (p instanceof Filma) {
				Filma f = (Filma) p;
				if (f.getGeneroa().equals(generoa)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean proiekzioaBadago(Proiekzioa proiekzioa, ArrayList<Proiekzioa> egunekoProiekzioak) {
		for (Proiekzioa p : egunekoProiekzioak) {
			if (p.getId() == proiekzioa.getId()) {
				return true;
			}
		}
		return false;
	}

	public static int hurrengoId(ArrayList<Proiekzioa> proiekzioak) {
		int id = 0;
		for (Proiekzioa p : proiekzioak) {
			if (p.getId() > id) {
				id = p.getId();
			}
		}
		return id + 1;
	}

	public static void listaBete(JList<String> listaEdukiEgunarenLaburpena, ArrayList<Proiekzioa> egunekoProiekzioak) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		for (Proiekzioa p : egunekoProiekzioak) {
			modelo.addElement(p.toLaburpenTestua());
		}
		listaEdukiEgunarenLaburpena.setModel(modelo);
	}

	public static void ezabatuProiekzioaListatik(JList<String> lista, ArrayList<Proiekzioa> proiekzioak) {
		int index = lista.getSelectedIndex();
		String testua = (String) lista.getSelectedValue();

		if (index != -1) {
			// documentar error, no se puede eliminar la fila de la lista, hay que hacerlo con el modelo
			DefaultListModel<String> modelo = (DefaultListModel<String>) lista.getModel();
			modelo.remove(index);
		}

		String id = testua.substring(0, testua.indexOf("-")).trim();

		for (int i = 0; i < proiekzioak.size(); i++) {
			if (proiekzioak.get(i).getId() == Integer.parseInt(id)) {
				proiekzioak.remove(i);
				return;
			}
		}
	}

	public static void ezabatuProiekzioaTaulatik(JTable taula, ArrayList<Proiekzioa> proiekzioak) {
		int row = taula.getSelectedRow();
		if (row != -1) {
			DefaultTableModel modelo = (DefaultTableModel) taula.getModel();
			modelo.removeRow(row);

			int id = (int) taula.getValueAt(row, 0);
			for (int i = 0; i < proiekzioak.size(); i++) {
				if (proiekzioak.get(i).getId() == id) {
					proiekzioak.remove(i);
					return;
				}
			}
		}
	}

	public static void egunLaburpenaBete(JList<String> listaEdukiEgunarenLaburpena, ArrayList<Proiekzioa> egunekoProiekzioak, String eguna) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		LocalTime ordua = LocalTime.of(16, 0, 0);
		String str = "";
		for (Proiekzioa p : egunekoProiekzioak) {
			str = p.toLaburpenTestua();
			str = str.concat(" - " +ordua.toString());
			ordua = ordua.plusMinutes(p.getIraupena());
			str = str.concat(" - " +ordua.toString());
			modelo.addElement(str);
		}
		listaEdukiEgunarenLaburpena.setModel(modelo);
	}

}
