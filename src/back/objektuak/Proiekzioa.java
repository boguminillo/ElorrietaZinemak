package back.objektuak;
/**
 * Proiekzioa
 */
public abstract class Proiekzioa {

	/** Proiekzioaren identifikatzailea */
	private int id;
	/** Proiekzioaren izenburua */
	private String izenburua;
	/** Proiekzioaren iraupena */
	private int iraupena;

	/**
	 * Eraikitzaile hutsa
	 */
	public Proiekzioa() {
		this.id = 0;
		this.izenburua = "";
		this.iraupena = 0;
	}

	/**
	 * Proiekzioaren identifikatzailea lortu
	 * 
	 * @return Proiekzioaren identifikatzailea
	 */
	public int getId() {
		return id;
	}

	/**
	 * Proiekzioaren identifikatzailea ezarri
	 * 
	 * @param id Proiekzioaren identifikatzailea
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Proiekzioaren izenburua lortu
	 * 
	 * @return Proiekzioaren izenburua
	 */
	public String getIzenburua() {
		return izenburua;
	}

	/**
	 * Proiekzioaren izenburua ezarri
	 * 
	 * @param izenburua Proiekzioaren izenburua
	 */
	public void setIzenburua(String izenburua) {
		this.izenburua = izenburua;
	}

	/**
	 * Proiekzioaren iraupena lortu
	 * 
	 * @return Proiekzioaren iraupena
	 */
	public int getIraupena() {
		return iraupena;
	}

	/**
	 * Proiekzioaren iraupena ezarri
	 * 
	 * @param iraupena Proiekzioaren iraupena
	 */
	public void setIraupena(int iraupena) {
		this.iraupena = iraupena;
	}

	@Override
	public String toString() {
		return id + ";" + izenburua + ";" + iraupena;
	}	

}