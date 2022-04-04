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