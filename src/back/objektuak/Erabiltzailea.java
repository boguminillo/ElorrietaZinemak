package back.objektuak;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Erabiltzailea
 */
public class Erabiltzailea {

	/** Erabiltzailearen login-a */
	private String login;
	/** Erabiltzailearen pasahitza */
	private String pasahitza;
	/** Erabiltzailearen izena */
	private String izena;
	/** Erabiltzailearen abizena */
	private String abizena;
	/** Erabiltzailearen jaiotze data */
	private LocalDate jaiotzeData;
	/** Erabiltzailearen funtzioa */
	private Funtzioak funtzioa;

	/**
	 * Eraikitzaile hutsa
	 */
	public Erabiltzailea() {
		this.login = "";
		this.pasahitza = "";
		this.izena = "";
		this.abizena = "";
		this.jaiotzeData = LocalDate.now();
		this.funtzioa = Funtzioak.EMPLEADO;
	}

	/**
	 * Erabiltzailea
	 * 
	 * @param login       Erabiltzailearen login-a
	 * @param pasahitza   Erabiltzailearen pasahitza
	 * @param izena       Erabiltzailearen izena
	 * @param abizena     Erabiltzailearen abizena
	 * @param jaiotzeData Erabiltzailearen jaiotze data
	 * @param funtzioa    Erabiltzailearen funtzioa
	 */
	public Erabiltzailea(String login, String pasahitza, String izena, String abizena, LocalDate jaiotzeData,
			Funtzioak funtzioa) {
		this.login = login;
		this.pasahitza = pasahitza;
		this.izena = izena;
		this.abizena = abizena;
		this.jaiotzeData = jaiotzeData;
		this.funtzioa = funtzioa;
	}

	/**
	 * Erabiltzailearen login-a lortu
	 * 
	 * @return Erabiltzailearen login-a
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Erabiltzailearen login-a ezarri
	 * 
	 * @param login Erabiltzailearen login-a
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Erabiltzailearen pasahitza lortu
	 * 
	 * @return Erabiltzailearen pasahitza
	 */
	public String getPasahitza() {
		return pasahitza;
	}

	/**
	 * Erabiltzailearen pasahitza ezarri
	 * 
	 * @param pasahitza Erabiltzailearen pasahitza
	 */
	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	/**
	 * Erabiltzailearen izena lortu
	 * 
	 * @return Erabiltzailearen izena
	 */
	public String getIzena() {
		return izena;
	}

	/**
	 * Erabiltzailearen izena ezarri
	 * 
	 * @param izena Erabiltzailearen izena
	 */
	public void setIzena(String izena) {
		this.izena = izena;
	}

	/**
	 * Erabiltzailearen abizena lortu
	 * 
	 * @return Erabiltzailearen abizena
	 */
	public String getAbizena() {
		return abizena;
	}

	/**
	 * Erabiltzailearen abizena ezarri
	 * 
	 * @param abizena Erabiltzailearen abizena
	 */
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	/**
	 * Erabiltzailearen jaiotze data lortu
	 * 
	 * @return Erabiltzailearen jaiotze data
	 */
	public LocalDate getJaiotzeData() {
		return jaiotzeData;
	}

	/**
	 * Erabiltzailearen jaiotze data ezarri
	 * 
	 * @param jaiotzeData Erabiltzailearen jaiotze data
	 */
	public void setJaiotzeData(LocalDate jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}

	/**
	 * Erabiltzailearen funtzioa lortu
	 * 
	 * @return Erabiltzailearen funtzioa
	 */
	public Funtzioak getFuntzioa() {
		return funtzioa;
	}

	/**
	 * Erabiltzailearen funtzioa ezarri
	 * 
	 * @param funtzioa Erabiltzailearen funtzioa
	 */
	public void setFuntzioa(Funtzioak funtzioa) {
		this.funtzioa = funtzioa;
	}

	@Override
	public String toString() {
		return login + ";" + pasahitza + ";" + izena + ";" + abizena + ";" + jaiotzeData + ";" + funtzioa + "\n";
	}

	/**
	 * Erabiltzailearen logina eta pasahitza fitxategian konprobatu eta beste datuak lortu
	 * 
	 * @return true logina ondo egin bada eta false errorerik egon bada
	 */
	public boolean login() {
		File file = new File("datuak/erabiltzaileak.csv");
		try (Scanner sc = new Scanner(file)){
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(";");
				if (data[0].equals(login) && data[1].equals(pasahitza)) {
					this.izena = data[2];
					this.abizena = data[3];
					this.jaiotzeData = LocalDate.parse(data[4]);
					this.funtzioa = Funtzioak.valueOf(data[5]);
					return true;
				} else if (data[0].equals(login) && !data[1].equals(pasahitza)) {
					return false;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Erabiltzailea fitxategian gorde
	 * 
	 * @return true gorde bada eta false logina existitzen bada
	 */
	public boolean erregistratu() {
		if (login.equals("") || pasahitza.equals("") || izena.equals("") || abizena.equals("") || jaiotzeData == LocalDate.now() || erregistratutaDago()) {
			return false;
		}
		File file = new File("datuak/erabiltzaileak.csv");
		try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
			pw.print(this.toString());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Erabiltzailea existitzen al duen konprobatu
	 * 
	 * @return true existitzen bada eta false existitzen ez bada
	 */
	public boolean erregistratutaDago(){
		File file = new File("datuak/erabiltzaileak.csv");
		if (!file.exists()) {
			return false;
		}
		try (Scanner sc = new Scanner(file)){
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(";");
				if (data[0].equals(login)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
