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

/**
 * Metodo erabilgarriak
 */
public class Erabilgarriak {

	/**
	 * Egun bakoitzeko denborak
	 */
	public static final Map<String, Integer> egunDenborak = Map.ofEntries(
			Map.entry("ASTELEHENA", 5 * 60),
			Map.entry("ASTEARTEA", 5 * 60),
			Map.entry("ASTEAZKENA", 5 * 60),
			Map.entry("OSTEGUNA", 6 * 60),
			Map.entry("OSTIRALA", 6 * 60),
			Map.entry("LARUNBATA", 8 * 60),
			Map.entry("IGANDEA", 8 * 60));

	/**
	 * Filmak identifikatzeko stringa
	 */
	public static final String FILMA = "Filma";
	/**
	 * Dokumentalak identifikatzeko stringa
	 */
	public static final String DOKUMENTALA = "Dokumentala";
	/**
	 * FilmLaburrak identifikatzeko stringa
	 **/
	public static final String FILMLABURRA = "FilmLaburra";

	/**
	 * Eraikitzaile pribatua
	 */
	private Erabilgarriak() {
	}

	/**
	 * Egun baten laburpena lortzeko metodoa. Egunan dauden proiekzio kopurua, batezbesteko iraupena eta denbora librea ematen du.
	 * 
	 * @param egunekoProiekzioak Proiekzioen zerrenda
	 * @param eguna Eguna
	 * @return Laburpena
	 */
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

	/**
	 * Proiekzioen zerrenda taula baten sartzeko metodoa
	 * 
	 * @param tableModel Taula baten modeloa
	 * @param proiekzioak Proiekzioen zerrenda
	 * @param mota Proiekzioen mota
	 */
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

	/**
	 * Taula baten proiekzioak ArrayList-ean sartzeko metodoa
	 * 
	 * @param modelo Taula baten modeloa
	 * @param mota Proiekzioen mota
	 * @return Proiekzioen zerrenda
	 */
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
	
	/**
	 * Proiekzio bat zerrenda batetik id-a erabiltzen lortzeko metodoa.
	 * 
	 * @param id Proiekzioaren id-a
	 * @param proiekzioak Proiekzioen zerrenda
	 * @return Proiekzioa
	 */
	public static Proiekzioa proiekzioaLortu(int id, ArrayList<Proiekzioa> proiekzioak) {
		for (Proiekzioa p : proiekzioak) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Egun baten denbora librea lortzeko metodoa
	 * 
	 * @param proiekzioak Eguneko proiekzioen zerrenda
	 * @param eguna Eguna
	 * @return Denbora librea
	 */
	public static int denboraLibre(ArrayList<Proiekzioa> proiekzioak, String eguna) {
		int denboraLibre = egunDenborak.get(eguna);
		for (Proiekzioa p : proiekzioak) {
			denboraLibre -= p.getIraupena();
		}
		return denboraLibre;
	}

	/**
	 * Proiekzio zerrenda baten genero bateko filma dagoen ala ez zehazten duen metodoa
	 * 
	 * @param generoa Bilatzeko generoa
	 * @param proiekzioak Proiekzioen zerrenda
	 * @return True generoa badago, false ez badago
	 */
	public static boolean generoaBadago(String generoa, ArrayList<Proiekzioa> proiekzioak) {
		for (Proiekzioa p : proiekzioak) {
			if (p instanceof Filma) {
				Filma f = (Filma) p;
				if (f.getGeneroa().equals(generoa)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Proiekzio zerrenda baten proiekzio bat dagoen ala ez zehazten duen metodoa
	 * 
	 * @param proiekzioa Bilatzeko proiekzioa
	 * @param proiekzioak Proiekzioen zerrenda
	 * @return True proiekzioa badago, false ez badago
	 */
	public static boolean proiekzioaBadago(Proiekzioa proiekzioa, ArrayList<Proiekzioa> proiekzioak) {
		for (Proiekzioa p : proiekzioak) {
			if (p.getId() == proiekzioa.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Proiekzio zerrenda baten libre dagoen lehenengo id-a lortzeko metodoa
	 * 
	 * @param proiekzioak Proiekzioen zerrenda
	 * @return Id-a
	 */
	public static int hurrengoId(ArrayList<Proiekzioa> proiekzioak) {
		int id = 0;
		for (Proiekzioa p : proiekzioak) {
			if (p.getId() > id) {
				id = p.getId();
			}
		}
		return id + 1;
	}

	/**
	 * Proiekzio zerrenda bat JList-ean sartzeko metodoa
	 * 
	 * @param lista Proiekzioen zerrenda (JList)
	 * @param proiekzioak Proiekzioen zerrenda
	 * @param eguna Eguna
	 */
	public static void listaBete(JList<String> lista, ArrayList<Proiekzioa> proiekzioak, String eguna) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		LocalTime ordua = LocalTime.of(16, 0, 0);
		String str = "";
		for (Proiekzioa p : proiekzioak) {
			str = p.toLaburpenTestua();
			str = str.concat(" - " +ordua.toString());
			ordua = ordua.plusMinutes(p.getIraupena());
			str = str.concat(" - " +ordua.toString());
			modelo.addElement(str);
		}
		lista.setModel(modelo);
	}

	/**
	 * JList baten autatutako proiekzioa proiekzio zerrenda batetik ezabatzeko metodoa
	 * 
	 * @param lista Proiekzioen zerrenda (JList)
	 * @param proiekzioak Proiekzioen zerrenda
	 */
	public static void ezabatuProiekzioaListatik(JList<String> lista, ArrayList<Proiekzioa> proiekzioak) {
		// int index = lista.getSelectedIndex();
		String testua = (String) lista.getSelectedValue();

		// if (index != -1) {
		// 	// documentar error, no se puede eliminar la fila de la lista, hay que hacerlo con el modelo
		// 	DefaultListModel<String> modelo = (DefaultListModel<String>) lista.getModel();
		// 	modelo.remove(index);
		// }

		String id = testua.substring(0, testua.indexOf("-")).trim();

		for (int i = 0; i < proiekzioak.size(); i++) {
			if (proiekzioak.get(i).getId() == Integer.parseInt(id)) {
				proiekzioak.remove(i);
				return;
			}
		}
	}

	/**
	 * JTable baten autatutako proiekzioa JTable-etik eta proiekzio zerrenda batetik ezabatzeko metodoa
	 * 
	 * @param taula Proiekzioen zerrenda (JTable)
	 * @param proiekzioak Proiekzioen zerrenda
	 */
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

}
