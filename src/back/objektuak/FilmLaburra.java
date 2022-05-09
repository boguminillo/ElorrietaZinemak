package back.objektuak;
/**
 * FilmLaburra
 */
public class FilmLaburra extends Proiekzioa {

	/**
	 * Filme laburraren fabula
	 */
	private String fabula;

	/**
	 * Eraikitzaile hutsa
	 */
	public FilmLaburra() {
		super();
		this.fabula = "";
	}

	/**
	 * Eraikitzailea
	 * 
	 * @param id Proiekzioaren identifikatzailea
	 * @param izenburua Proiekzioaren izenburua
	 * @param iraupena Proiekzioaren iraupena
	 * @param fabula Filme laburraren fabula
	 **/
	public FilmLaburra(int id, String izenburua, int iraupena, String fabula) {
		super(id, izenburua, iraupena);
		this.fabula = fabula;
	}

	/**
	 * Filme laburraren fabula lortu
	 * 
	 * @return Filme laburraren fabula
	 */
	public String getFabula() {
		return fabula;
	}

	/**
	 * Filme laburraren fabula ezarri
	 * 
	 * @param fabula Filme laburraren fabula
	 */
	public void setFabula(String fabula) {
		this.fabula = fabula;
	}

	@Override
	public String toString() {
		return "FilmLaburra;" + super.toString() + ";" + fabula + "\n";
	}
	
}