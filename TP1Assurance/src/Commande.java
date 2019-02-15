
public class Commande {

	private Client client;
	private String plat;
	private int qte;

	public Commande() {

	}

	public Commande( Client client, String plat, int qte ) {
		this.client = client;
		this.plat = plat;
		this.qte = qte;

	}

	public String getNomClient() {
		return this.client.getNom();
	}

	public String getPlat() {
		return this.plat;
	}

	public int getQte() {
		return this.qte;
	}
}
