
public class Client {

	private String nom;

	public Client() {

	}
	// Constreur avec param�tre complet

	public Client( String nom ) {
		this.nom = nom;

	}

	// Methode accesseur

	public void setNom( String nom ) {
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}
}
