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
