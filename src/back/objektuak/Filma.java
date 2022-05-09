package back.objektuak;
/**
 * Filma
 */
public class Filma extends FilmLuzea {
	
	/** Filmaren PEGI */
	private int pegi;
	/** Filmaren generoa */
	private String generoa;

	/**
	 * Eraikitzaile hutsa
	 */
	public Filma() {
		super();
		this.pegi = 0;
		this.generoa = "";
	}

	/**
	 * Eraikitzailea
	 * 
	 * @param id Proiekzioaren identifikatzailea
	 * @param izenburua Proiekzioaren izenburua
	 * @param iraupena Proiekzioaren iraupena
	 * @param produktora Filmaren produktora
	 * @param pegi Filmaren PEGI
	 **/
	public Filma(int id, String izenburua, int iraupena, String produktora, int pegi, String generoa) {
		super(id, izenburua, iraupena, produktora);
		this.pegi = pegi;
		this.generoa = generoa;
	}

	/**
	 * Filmaren PEGI lortu
	 * 
	 * @return Filmaren PEGI
	 */
	public int getPegi() {
		return pegi;
	}

	/**
	 * Filmaren PEGI ezarri
	 * 
	 * @param pegi Filmaren PEGI
	 */
	public void setPegi(int pegi) {
		this.pegi = pegi;
	}

	/**
	 * Filmaren generoa lortu
	 * 
	 * @return Filmaren generoa
	 */
	public String getGeneroa() {
		return generoa;
	}

	/**
	 * Filmaren generoa ezarri
	 * 
	 * @param generoa Filmaren generoa
	 */
	public void setGeneroa(String generoa) {
		this.generoa = generoa;
	}

	@Override
	public String toString() {
		return "Filma;" + super.toString() + ";" + pegi + ";" + generoa + "\n";
	}
	
}