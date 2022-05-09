package back.objektuak;
/**
 * Dokumentala
 */
public class Dokumentala extends FilmLuzea {
	
	/**
	 * Dokumentalaren tema
	 */
	private String tema;

	/**
	 * Eraikitzaile hutsa
	 */
	public Dokumentala() {
		super();
		this.tema = "";
	}

	/**
	 * Eraikitzailea
	 * 
	 * @param id Proiekzioaren identifikatzailea
	 * @param izenburua Proiekzioaren izenburua
	 * @param iraupena Proiekzioaren iraupena
	 * @param produktora Filmaren produktora
	 * @param tema Dokumentalaren tema
	 **/
	public Dokumentala(int id, String izenburua, int iraupena, String produktora, String tema) {
		super(id, izenburua, iraupena, produktora);
		this.tema = tema;
	}

	/**
	 * Dokumentalaren tema lortu
	 * 
	 * @return Dokumentalaren tema
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * Dokumentalaren tema ezarri
	 * 
	 * @param tema Dokumentalaren tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "Dokumentala;" + super.toString() + ";" + tema + "\n";
	}
	
}