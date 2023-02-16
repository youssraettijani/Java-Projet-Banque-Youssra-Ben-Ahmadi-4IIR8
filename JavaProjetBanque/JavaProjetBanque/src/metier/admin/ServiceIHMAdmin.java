package metier.admin;

import metier.InteractiveConsole;
import presentation.modele.Banque;

public class ServiceIHMAdmin implements IServiceIHMAdmin, InteractiveConsole{

	Banque banque;
	public ServiceIHMAdmin(Banque banque) {this.banque = banque;}

	@Override
	public int menuGlobal() {
		System.out.println("------------------ Menu Admin  -------------------");
		System.out.println("Taper [1] pour Services CRUD");
		System.out.println("Taper [2] pour les Information de la banque");
		System.out.println("Taper [3] pour Services Trie");
		System.out.println("Taper [4] pour Le tableau de bord");
		System.out.println("Taper [0] pour se deconnecter");
		System.out.println("-------------------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

	@Override
	public int menuModification() {
		System.out.println("------------------ Menu Modif  -------------------");
		System.out.println("Taper [1] pour Menu Recherche");
		System.out.println("Taper [2] pour Menu Modification");
		System.out.println("Taper [3] pour Menu Ajout");
		System.out.println("Taper [4] pour Menu suppression");
		System.out.println("Taper [0] pour Revenir en arriere");
		System.out.println("-------------------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

	@Override
	public int menuRecherche() {
		System.out.println("------------------ Menu Recherche  ---------------");
		System.out.println("Taper [1] pour chercher Client par id");
		System.out.println("Taper [2] pour chercher Client par nom");
		System.out.println("Taper [3] pour chercher Client par prenom");
		System.out.println("Taper [4] pour chercher Client par CIN");
		System.out.println("Taper [5] pour chercher Client par email");
		System.out.println("Taper [6] pour chercher Compte par id");
		System.out.println("Taper [7] pour chercher Compte par solde");
		System.out.println("Taper [8] pour chercher Compte par date creation");
		System.out.println("Taper [9] pour chercher Compte par proprietaire");
		System.out.println("Taper [0] pour Revenir en arriere");
		System.out.println("-------------------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

	@Override
	public int menuInformations() {
		return 0;
	}

	public int menuCRUD() {
		System.out.println("------------------ Menu CRUD  -------------------");
		System.out.println("Taper [1] pour ajouter");
		System.out.println("Taper [2] pour Modifier");
		System.out.println("Taper [3] pour Supprimer");
		System.out.println("Taper [4] pour afficher");
		System.out.println("Taper [0] pour Revenir en arriere");
		System.out.println("-------------------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}
	@Override
	public int menuAjout() {
		System.out.println("------------------ Menu Ajout  -------------------");
		System.out.println("Taper [1] pour ajouter Client");
		System.out.println("Taper [2] pour ajouter Compte avec client existant");
		System.out.println("Taper [0] pour Revenir en arriere");
		System.out.println("-------------------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

	@Override
	public int menuSuppression() {
		return 1;
	}

	@Override
	public int tableauDeBord() {
		return 1;
	}

	@Override
	public int menuTrie() {
		System.out.println("------------------ Menu Tri ---------------------");
		System.out.println("Taper [1] pour trier Client par nom");
		System.out.println("Taper [2] pour trier Client par CIN");
		System.out.println("Taper [3] pour trier Client par Email");
		System.out.println("Taper [4] pour trier Client par Solde Compte");
		System.out.println("Taper [5] pour trier Compte par Solde");
		System.out.println("Taper [6] pour trier Compte par date creation");
		System.out.println("Taper [7] pour trier Compte par proprietaire");
		System.out.println("Taper [0] pour Revenir en arriere");
		System.out.println("-------------------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		return choix;
	}

	@Override
	public int menuComptabilité() {
		return 1;
	}

}
