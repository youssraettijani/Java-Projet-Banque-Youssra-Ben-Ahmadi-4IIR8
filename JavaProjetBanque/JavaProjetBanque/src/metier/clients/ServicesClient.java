package metier.clients;

import java.util.List;

import metier.InteractiveConsole;
import metier.authentification.ServiceAuth;
import metier.clients.ServicesClient;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;

public class ServicesClient implements IServiceClient, InteractiveConsole{

	private Client client;
	private Compte compte;
	public ServicesClient(Client client) {
		this.client = client;
	}
	
	
	public Client getClient() {return client;}
	public void setClient(Client client) {this.client = client;}
	
	@Override
	public boolean versement() {
			System.out.println("Entre le montant à verser");
			Double montant = Double.parseDouble(clavier.nextLine());
			if(montant > 0) {
				int index = client.getComptesClient().indexOf(compte);
				compte.setSolde(compte.getSolde() + montant);
				String message = "Versement de " + montant;
				compte.setLog(TypeLog.VERSEMENT, message);
				List<Compte> listComptes = client.getComptesClient();
				listComptes.set(index, compte);
				client.setComptesClient(listComptes);
				System.out.println("Tapper 1 pour avoir le ticket de transaction : ");
				int choix = Integer.parseInt(clavier.nextLine());
				if(choix == 1)
					afficherTicket();
				return true;
			}
		return false;
	}

	@Override
	public boolean retrait() {
		System.out.println("Entre le montant à verser");
		Double montant = Double.parseDouble(clavier.nextLine());
		if(montant > 0) {
			if(compte.getSolde() >= montant) {
				int index = client.getComptesClient().indexOf(compte);
				compte.setSolde(compte.getSolde() - montant);
				String message = "Retrait de " + montant;
				compte.setLog(TypeLog.RETRAIT, message);
				List<Compte> listComptes = client.getComptesClient();
				listComptes.set(index, compte);
				client.setComptesClient(listComptes);
				System.out.println("Tapper 1 pour avoir le ticket de transaction : ");
				int choix = Integer.parseInt(clavier.nextLine());
				if(choix == 1)
					afficherTicket();
				return true;
			}else {
				System.out.println("Fonds insuffisants!!!!");
			}
		}
		return false;
	}
	
	public boolean retrait(Double montant) {
		if(montant > 0) {
			if(compte.getSolde() >= montant) {
				int index = client.getComptesClient().indexOf(compte);
				compte.setSolde(compte.getSolde() - montant);
				String message = "Retrait de " + montant;
				compte.setLog(TypeLog.RETRAIT, message);
				List<Compte> listComptes = client.getComptesClient();
				listComptes.set(index, compte);
				client.setComptesClient(listComptes);
				System.out.println("Tapper 1 pour avoir le ticket de transaction : ");
				int choix = Integer.parseInt(clavier.nextLine());
				if(choix == 1)
					afficherTicket();
				return true;
			}else {
				System.out.println("Fonds insuffisants!!!!");
			}
		}
		return false;
	}

	@Override
	public boolean retrait(int choixRetrait) {
		switch(choixRetrait){
			case 1 :
				return retrait(100.0);
			case 2 :
				return retrait(200.0);
			case 3 :
				return retrait(300.0);
			case 4 :
				return retrait(500.0);
			case 5 :
				return retrait();
			case 0 :
				menu();
				return true;
		}
		return false;
	}

	@Override
	public boolean virement() {
		System.out.println("-------------- Virement --------------");
		System.out.println("-------------- Client R --------------");
		// compteReceveur = choisirCompte();
		System.out.print("Montant du virement : ");
		Double montant = Double.parseDouble(clavier.nextLine());
		retrait(montant);
		
		return true;
	}

	@Override
	public boolean modifierProfile(int choixModification) {
		System.out.println("-------------- Modification --------------");
		switch(choixModification){
	       case 1: 
	           System.out.print("Entrer le nouveau log : ");
	           String log = clavier.nextLine();
	           client.setLogin(log);
	           System.out.println("Log change");
	           return true;
	       case 2:
	    	   System.out.print("Entrer le nouveau mdp : ");
	           String mdp = clavier.nextLine();
	           client.setMotDePasse(mdp);
	           System.out.println("mdp change");
	           return true;
	       case 3: 
	           System.out.print("Entrer le nouveau nom : ");
	           String nom = clavier.nextLine();
	           client.setNom(nom);
	           System.out.println("nom change");
	           return true;
	       case 4:
	    	   System.out.print("Entrer le nouveau prenom : ");
	           String prenom = clavier.nextLine();
	           client.setPrenom(prenom);
	           System.out.println("prenom change");
	           return true;
	       case 5: 
	           System.out.print("Entrer le nouveau mail : ");
	           String mail = clavier.nextLine();
	           client.setEmail(mail);
	           System.out.println("mail change");
	           return true;
	       case 6:
	    	   System.out.print("Entrer le nouveau tel : ");
	           String tel = clavier.nextLine();
	           client.setTel(tel);
	           System.out.println("tel change");
	           return true;
	       case 7: 
	           System.out.print("Entrer le nouveau CIN : ");
	           String cin = clavier.nextLine();
	           client.setCin(cin);
	           System.out.println("cin change");
	           return true;
	       case 0:
	    	   menu();
	    	   return true;
	       default:
	           System.out.println("Choix incorrect");
	           break;
	   }
		System.out.println("--------------------------------------------------");
		return false;
	}

	@Override
	public void dernièresOpérations() {
		List<Log> logs = compte.getLogs();
		for(Log log : logs) {
			System.out.println(log.toString());
		}
	}

	@Override
	public Double afficherSolde() {
		return compte.getSolde();
	}

	@Override
	public Compte choisirCompte() {
		System.out.println("----------------- Choisir Compte -----------------");
		for(Compte compte : client.getComptesClient()) {
			int i = 1;
			if(i <= client.getComptesClient().size()) {
				System.out.println("Taper ["+ compte.getNumeroCompte() + "] pour choisir le compte " + compte.getNumeroCompte());
				i++;
			}
		}
		String choix = clavier.nextLine();
		System.out.println("--------------------------------------------------");
		for(Compte compte : client.getComptesClient()) {
			if(compte.getNumeroCompte().equals(choix))
				return compte;
		}
		return null;
	}

	@Override
	public void afficherTicket() {
		System.out.println("--------------- Ticket transaction ---------------");
		System.out.println(compte.getLogs().get(compte.getLogs().size() - 1).toString());
		System.out.println("--------------------------------------------------");
	}

	public void choixCompte() {
		do {
			compte = choisirCompte();
		}while(compte == null);
	}
	
	public void menuDepart() {
		choixCompte();
		menu();
	}
	
	public void menuInfos(int key) {
		switch (key) {
		case 1:
	    	   System.out.println("-------------- Dernieres operations --------------");
	    	   dernièresOpérations();
	    	   System.out.println("--------------------------------------------------");
	    	   menu();
	    case 2:
	    	   System.out.println("------------------ Votre Solde -------------------");
	    	   System.out.println("Solde Compte " + compte.getNumeroCompte() + " : " + afficherSolde());
	    	   System.out.println("--------------------------------------------------");
	    	   menu();
	    case 3:
	    	   System.out.println(client.toString());
	    	   menu();
	    case 0:
	    	menu();
		default:
			System.out.println("Choix incorrect");
	           menu();
		}
	}
	
	public void menu() {
		ServiceIHMClient menuG = new ServiceIHMClient();
		int choix = menuG.menuGlobal();
		
		switch(choix){
	       case 1: 
	           versement();
	           menu();
	       case 2:
	    	   int choixRetrait = menuG.menuRetrait();
	    	   retrait(choixRetrait);
	    	   menu();
	       case 3:
	    	   int choixModification = menuG.menuModification();
	    	   modifierProfile(choixModification);
	    	   menu();
	       case 4:
	    	   int choixInfos = menuG.menuInformations();
	    	   menuInfos(choixInfos);
	       case 0:
	    	   ServiceAuth deconnexion = new ServiceAuth(null);
	    	   deconnexion.SeDéconnecter();
	    	   break;
	       default:
	           System.out.println("Choix incorrect");
	           menu();
	           break;
	   }
	}
}
