
public class Plat {

	private String plat;
	private double prix;

	public Plat() {

	}

	public Plat( String plat, double prix ) {
		this.plat = plat;
		this.prix = prix;

	}

	public void setPlat( String plat ) {
		this.plat = plat;
	}

	public void setPrix( double prix ) {
		this.prix = prix;
	}

	public String getPlat() {
		return this.plat;
	}

	public double getPrix() {
		return this.prix;
	}
}
