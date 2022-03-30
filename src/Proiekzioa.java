/**
 * Proiekzioak
 */
public abstract class Proiekzioa {

	private int id;
	private String izenburua;
	private int denbora;

	public Proiekzioa() {
		this.id = 0;
		this.izenburua = "";
		this.denbora = 0;
	}

	public Proiekzioa(int id, String izenburua, int denbora) {
		this.id = id;
		this.izenburua = izenburua;
		this.denbora = denbora;
	}

}