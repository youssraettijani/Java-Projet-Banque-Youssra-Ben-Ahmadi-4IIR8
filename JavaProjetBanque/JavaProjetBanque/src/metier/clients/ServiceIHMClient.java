package metier.clients;

import metier.InteractiveConsole;

public class ServiceIHMClient implements IServiceIHMClient, InteractiveConsole{

	public ServiceIHMClient() {}

	@Override
	public int menuGlobal() {
		System.out.println("------------------ Menu Compte -------------------");
		System.out.println("Taper [1] pour faire un depot");
		System.out.println("Taper [2] pour faire un retrait");
		System.out.println("Taper [3] pour modifier infos du compte");
		System.out.println("Taper [4] pour plus d'infos sur le compte");
		System.out.println("Taper [0] pour se deconnecter");
		System.out.println("-------------------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

	@Override
	public int menuModification() {
		System.out.println("--------- Modifier profil ----------");
		System.out.println("Taper [1] pour changer le log");
		System.out.println("Taper [2] pour changer le mdp");
		System.out.println("Taper [3] pour changer le nom");
		System.out.println("Taper [4] pour changer le prenom");
		System.out.println("Taper [5] pour changer le mail");
		System.out.println("Taper [6] pour changer le tel");
		System.out.println("Taper [7] pour changer le CIN");
		System.out.println("Taper [0] pour le menu principal");
		System.out.println("------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

	@Override
	public int menuRetrait() {
		System.out.println("------------------------------------");
		System.out.println("Taper [1] pour retirer       100 Dhs");
		System.out.println("Taper [2] pour retirer       200 Dhs");
		System.out.println("Taper [3] pour retirer       300 Dhs");
		System.out.println("Taper [4] pour retirer       500 Dhs");
		System.out.println("Taper [5] pour retirer autre montant");
		System.out.println("Taper [0] pour le menu principal");
		System.out.println("------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

	@Override
	public int menuInformations() {
		System.out.println("------------------------------------");
		System.out.println("Taper [1] pour afficher les dernieres operations");
		System.out.println("Taper [2] pour afficher Solde");
		System.out.println("Taper [3] pour afficher infos client");
		System.out.println("Taper [0] pour le menu principal");
		System.out.println("------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

}
