// Charles Létourneau et Ioana Grecu

import java.io.File;
import java.io.FileInputStream;

public class GestionFichier {

	private static final int NB_MAX = 10;

	public static void main(String[] args) {

		Client[] client = new Client[NB_MAX];
		Plat[] plat = new Plat[NB_MAX];
		Commande[] commande = new Commande[NB_MAX];

		try {
			File file = new File("FichierTest.txt");
			FileInputStream fis = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fis.read(bytes);
			fis.close();

			client = lireClients(bytes);
			plat = lirePlats(bytes);
			commande = lireCommandes(bytes);
			int qte;
			double prix;
			String nomPlat;

			if (commande != null && Erreur(commande, client)) {

				for (int i = 0; i < client.length && client[i] != null; ++i) {
					double totalFacture = 0;
					String nomClient = client[i].getNom();

					for (int j = 0; j < commande.length && commande[j] != null; ++j) {

						if (commande[j].getNom().equals(nomClient)) {
							qte = commande[j].getQte();
							nomPlat = commande[j].getPlat();
							prix = getCoutPlat(plat, nomPlat);
							totalFacture += (qte * prix);
						}
					}
					System.out.println(nomClient + " " + totalFacture + "$");
				}

			} else {
				System.out.println("Le fichier ne respecte pas le format demandé!");
			}
		} catch (Exception e) {

		}
	}

	public static double getCoutPlat(Plat[] plat, String nomPlat) {

		double cout = 0;
		for (int i = 0; i < plat.length && plat[i] != null; ++i) {

			if (plat[i].getPlat().equals(nomPlat)) {
				cout = plat[i].getPrix();
				break;
			}
		}
		return cout;
	}

	public static Client[] lireClients(byte[] bytes) {

		Client[] clients = new Client[NB_MAX];
		try {
			String text = new String(bytes);
			String client = text.substring(text.indexOf("Clients :"), text.lastIndexOf("Plats :"));
			String[] listesClients = client.split("\r\n");

			for (int i = 1; i < listesClients.length; ++i) {
				clients[i - 1] = new Client(listesClients[i]);
			}

		} catch (Exception e) {
		}
		return clients;
	}

	public static Plat[] lirePlats(byte[] bytes) {

		Plat[] plats = new Plat[NB_MAX];

		try {
			String texte = new String(bytes);
			String nomPlat = texte.substring(texte.indexOf("Plats :"), texte.lastIndexOf("Commandes :"));
			String[] listePlats = nomPlat.split("\r\n");

			for (int i = 1; i < listePlats.length; ++i) {
				String plat = listePlats[i].split(" ")[0];
				String cout = listePlats[i].split(" ")[1];
				Plat pla = new Plat(plat, (Double.parseDouble(cout)));
				plats[i - 1] = pla;
			}

		} catch (Exception e) {
		}
		return plats;
	}

	public static Commande[] lireCommandes(byte[] bytes) {

		Commande[] CommandesListe = new Commande[NB_MAX];

		try {
			String text = new String(bytes);
			String commande = text.substring(text.indexOf("Commandes :"), text.lastIndexOf("Fin"));
			String[] listesCommandes = commande.split("\r\n");

			for (int i = 1; i < listesCommandes.length; ++i) {

				String client = listesCommandes[i].split(" ")[0];
				String nomPlat = listesCommandes[i].split(" ")[1];
				String qte = listesCommandes[i].split(" ")[2];

				Client cliobj = new Client(client);
				Commande com = new Commande(cliobj, nomPlat, Integer.parseInt(qte));

				CommandesListe[i - 1] = com;
			}

		} catch (Exception e) {

			return null;
		}
		return CommandesListe;
	}

	public static boolean Erreur(Commande[] commande, Client[] client) {

		boolean present = false;

		for (int i = 0; i < commande.length && commande[i] != null; ++i) {
			String nomClient = commande[i].getNom();

			for (int j = 0; j < client.length && client[j] != null; ++j) {

				if (nomClient.equals(client[j].getNom())) {
					present = true;
					break;
				}
			}
			if (!present) {
				break;
			}
		}
		return present;
	}
}
