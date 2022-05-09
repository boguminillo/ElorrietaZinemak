package back.objektuak;
/**
 * FilmLuzea
 */
public abstract class FilmLuzea extends Proiekzioa {
		
	/** Filmaren produktora */
	private String produktora;

	/**
	 * Eraikitzaile hutsa
	 */
	public FilmLuzea() {
		super();
		this.produktora = "";
	}

	/**
	 * Eraikitzailea
	 * 
	 * @param id Proiekzioaren identifikatzailea
	 * @param izenburua Proiekzioaren izenburua
	 * @param iraupena Proiekzioaren iraupena
	 * @param produktora Filmaren produktora
	 **/
	public FilmLuzea(int id, String izenburua, int iraupena, String produktora) {
		super(id, izenburua, iraupena);
		this.produktora = produktora;
	}

	/**
	 * Filmaren produktora lortu
	 * 
	 * @return Filmaren produktora
	 */
	public String getProduktora() {
		return produktora;
	}

	/**
	 * Filmaren produktora ezarri
	 * 
	 * @param produktora Filmaren produktora
	 */
	public void setProduktora(String produktora) {
		this.produktora = produktora;
	}

	@Override
	public String toString() {
		return super.toString() + ";" + produktora;
	}
	
}
