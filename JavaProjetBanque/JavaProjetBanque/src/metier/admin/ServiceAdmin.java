package metier.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import metier.InteractiveConsole;
import metier.authentification.Deconnexion;
import metier.authentification.ServiceAuth;
import metier.clients.ServiceIHMClient;
import metier.clients.ServicesClient;
import presentation.modele.Banque;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Sexe;
import presentation.modele.TableauDeBord;
import presentation.modele.TypeLog;

public class ServiceAdmin extends ServiceAuth implements IServiceAdmin, InteractiveConsole{

	
	public ServiceAdmin(Banque banque) {
		super(banque);
	}
	

	@Override
	public Client nouveauClient() {
		String login, pass, n, p, mail, cin, tel, s;
		Sexe sexe;
		System.out.println("-----------Ajouter Client------------------");
		System.out.print("Entrer nom : ");
		n = clavier.nextLine();
		System.out.print("Entrer prenom : ");
		p = clavier.nextLine();
		System.out.print("Entrer login : ");
		login = clavier.nextLine();
		System.out.print("Entrer password : ");
		pass = clavier.nextLine();
		System.out.print("Entrer mail : ");
		mail = clavier.nextLine();
		System.out.print("Entrer cin : ");
		cin = clavier.nextLine();
		System.out.print("Entrer tel : ");
		tel = clavier.nextLine();
		System.out.print("Entrer le sexe (h pour homme et f pour femme : ");
		s = clavier.nextLine();
		if(s == "f" || s == "F") {
			sexe = Sexe.FEMME;
		}else {
			sexe = Sexe.HOMME;
		}
		return new Client(login, pass, n, p, mail, cin, tel,sexe);
	}

	@Override
	public Client nouveauCompteClientExistant() {
		Long id;
		System.out.println("-----------Ajouter Compte------------------");
		System.out.print("Entrer l'id : ");
		id = Long.parseLong(clavier.nextLine());
		Client client = chercherClientParId(id);
		if(client != null) {
			System.out.println("Tapper oui pour ajouté un montant : ");
			String y = clavier.nextLine();
			double mnt = 0;
			if(y.equals("oui") || y.equals("Oui")) {
				System.out.println("Entrer le montant a ajouté : ");
				mnt = Double.parseDouble(clavier.nextLine());
			}
			List<Compte> comptesClient = client.getComptesClient();
			Compte compte = new Compte(mnt, client);
			String log = "Creaation du Compte " + compte.getNumeroCompte();
			compte.setLog(TypeLog.CREATION, log);
			comptesClient.add(compte);
			client.setComptesClient(comptesClient);
			return client;
		}
		return null;
	}

	@Override
	public Client chercherClientParId(Long id) {
		Banque banque  = this.getBanque();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients)
			if(client.getId() == id)
				return client;
		return null;
	}

	@Override
	public List<Client> chercherClientParNom(String nom) {
		Banque banque  = this.getBanque();
		List<Client> clientsNom = new ArrayList<Client>();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients)
			if(client.getNom().equals(nom))
				clientsNom.add(client);
		if(clientsNom.size() != 0)
			return clientsNom;
		return null;
	}

	@Override
	public List<Client> chercherClientParPrénom(String prenom) {
		Banque banque  = this.getBanque();
		List<Client> clientsPrenom = new ArrayList<Client>();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients)
			if(client.getPrenom().equals(prenom))
				clientsPrenom.add(client);
		if(clientsPrenom.size() != 0)
			return clientsPrenom;
		return null;
	}

	@Override
	public Client chercherClientParCin(String cin) {
		Banque banque  = this.getBanque();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients)
			if(client.getCin().equals(cin))
				return client;
		return null;
	}

	@Override
	public Client chercherClientParEmail(String email) {
		Banque banque  = this.getBanque();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients)
			if(client.getEmail().equals(email))
				return client;
		return null;
	}

	@Override
	public Compte chercherCompteParId(String idCompte) {
		Banque banque  = this.getBanque();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients) {
			List<Compte> comptes = client.getComptesClient();
			for(Compte compte : comptes)
				if(compte.getNumeroCompte().equals(idCompte))
					return compte;
		}
		return null;
	}

	@Override
	public List<Compte> chercherCompteParSolde(double solde) {
		Banque banque  = this.getBanque();
		List<Compte> comptesSolde = new ArrayList<>();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients) {
			List<Compte> comptes = client.getComptesClient();
			for(Compte compte : comptes)
				if(compte.getSolde() == solde)
					comptesSolde.add(compte);
		}
		if(comptesSolde.size() != 0)
			return comptesSolde;
		return null;
	}

	@Override
	public List<Compte> chercherCompteParDateCreation(LocalDateTime date) {
		Banque banque  = this.getBanque();
		List<Compte> comptesDate = new ArrayList<>();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients) {
			List<Compte> comptes = client.getComptesClient();
			for(Compte compte : comptes)
				if(compte.getDateCreation().equals(date))
					comptesDate.add(compte);
		}
		return comptesDate;
	}

	@Override
	public List<Compte> chercherCompteParPropriétaire(Client propriétaire) {
		Banque banque  = this.getBanque();
		List<Client> clients = banque.getClientsDeBanque();
		for(Client client : clients) 
			if(client.equals(propriétaire))
				return client.getComptesClient();
		return null;
	}

	@Override
	public Client modifierClient(String filtre) {
		Long id = Long.parseLong(filtre);
		if(chercherClientParId(id) != null) {
			ServicesClient sC = new ServicesClient(chercherClientParId(id));
			ServiceIHMClient menuG = new ServiceIHMClient();
			int choix = menuG.menuModification();
			sC.modifierProfile(choix);
			Banque banque = getBanque();
			List<Client> clients = banque.getClientsDeBanque();
			int index = clients.indexOf(sC.getClient());
			clients.set(index, sC.getClient());
			banque.setClientsDeBanque(clients);
			setBanque(banque);
			return sC.getClient();
		}	
		return null;
	}

	@Override
	public boolean supprimerClient(Long id) {
		if(chercherClientParId(id) == null )
			return false;
		else {
			Banque banque = getBanque();
			List<Client> clients = getBanque().getClientsDeBanque();
			clients.remove(clients.indexOf(chercherClientParId(id)));
			banque.setClientsDeBanque(clients);
			setBanque(banque);
			return true;
		}
	}

	@Override
	public List<Client> trierClientParNom() {
		List<Client> clients = new ArrayList<>();
		for(Client client : this.getBanque().getClientsDeBanque())
			clients.add(client);
		Collections.sort(clients, (client1, client2) -> client1.getNomComplet().compareTo(client2.getNomComplet()));
		return clients;
	}

	@Override
	public List<Client> trierClientParCin() {
		List<Client> clients = new ArrayList<>();
		for(Client client : this.getBanque().getClientsDeBanque())
			clients.add(client);
		Collections.sort(clients, (client1, client2) -> client1.getCin().compareTo(client2.getCin()));
		return clients;
	}

	@Override
	public List<Client> trierClientParEmail() {
		List<Client> clients = new ArrayList<>();
		for(Client client : this.getBanque().getClientsDeBanque())
			clients.add(client);
		Collections.sort(clients, (client1, client2) -> client1.getEmail().compareTo(client2.getEmail()));
		return clients;
	}

	@Override
	public List<Client> trierClientParSoldeCompte() {
		List<Client> clients = new ArrayList<>();
		for(Client client : this.getBanque().getClientsDeBanque())
			clients.add(client);
		Collections.sort(clients, (client1, client2) -> client1.maxSolde().compareTo(client2.maxSolde()));
		return clients;
	}

	@Override
	public List<Compte> trierComptesParSolde() {
		List<Compte> comptes = new ArrayList<>();
		for(Client client : this.getBanque().getClientsDeBanque()) {
			for(Compte compte : client.getComptesClient())
				comptes.add(compte);
		}
		Collections.sort(comptes, (compte1, compte2) -> compte1.getSolde().compareTo(compte2.getSolde()));
		return comptes;
	}

	@Override
	public List<Compte> trierComptesParDateDeCreation() {
		List<Compte> comptes = new ArrayList<>();
		for(Client client : this.getBanque().getClientsDeBanque()) {
			for(Compte compte : client.getComptesClient())
				comptes.add(compte);
		}
		Collections.sort(comptes, (compte1, compte2) -> compte1.getDateCreation().compareTo(compte2.getDateCreation()));
		return comptes;
	}

	@Override
	public List<Compte> trierComptesParNomPropriétaire() {
		List<Compte> comptes = new ArrayList<>();
		for(Client client : this.getBanque().getClientsDeBanque()) {
			for(Compte compte : client.getComptesClient())
				comptes.add(compte);
		}
		Collections.sort(comptes, (compte1, compte2) -> compte1.getPropriétaire().getNomComplet().compareTo(compte2.getPropriétaire().getNomComplet()));
		return comptes;
	}
	
	public List<Client> clientFM(String sexe){
		List<Client> clients = new ArrayList<>();
		for(Client client : this.getBanque().getClientsDeBanque())
			if(client.getSexe().getAbreviation() == sexe)
				clients.add(client);
		return clients;
	}

	@Override
	public TableauDeBord calculerEtAfficherStatistiques() {
		TableauDeBord tableauDeBord = new TableauDeBord();
		tableauDeBord.setMaxSolde(trierComptesParSolde().get(trierComptesParSolde().size() - 1).getSolde());
		tableauDeBord.setMinSolde(trierComptesParSolde().get(0).getSolde());
		tableauDeBord.setNombreTotaleClient((long)trierClientParNom().size());
		tableauDeBord.setNombreTotaleCompte((long) trierComptesParSolde().size());
		tableauDeBord.setNomClientLePlusRiche(trierClientParSoldeCompte().get(trierClientParSoldeCompte().size() - 1).getNomComplet());
		tableauDeBord.setTotalClientsFemme((long)clientFM("F").size());
		tableauDeBord.setTotaleClientsHomme((long)clientFM("H").size());
		return tableauDeBord;
	}
	
	public void rechercheMenu() {
		System.out.println("------------------ Recherche  -------------------");
		System.out.println("Taper [1] pour chercher compte ");
		System.out.println("Taper [2] pour chercher client");
		System.out.println("Taper [0] pour revenir en arriere");
		System.out.println("-------------------------------------------------");
		System.out.print("Choix : ");
		int choix = Integer.parseInt(clavier.nextLine());
		switch(choix){
	       case 1: 
	    	   	System.out.println("------------------ Recherche Client -------------");
		   		System.out.println("Taper [1] pour chercher client par id");
		   		System.out.println("Taper [2] pour chercher client par nom");
		   		System.out.println("Taper [3] pour chercher client par prenom");
		   		System.out.println("Taper [4] pour chercher client par CIN");
		   		System.out.println("Taper [5] pour chercher client par email");
		   		System.out.println("Taper [0] pour revenir en arriere");
		   		System.out.println("-------------------------------------------------");
		   		System.out.print("Choix : ");
		   		int choixCl = Integer.parseInt(clavier.nextLine());
		   		switch(choixCl){
		   			case 1: 
		   				System.out.println("---------------------------------------------");
		    	   		System.out.println("Entrer id du client a afficher");
		    	   		Long idCl = Long.parseLong(clavier.nextLine());
		    	   		Client c1 = chercherClientParId(idCl);
		    	   		if(c1 != null) {
		    	   			System.out.println(c1.toString());
		    	   			menu();
		    	   		}
		    	   		else {
		    	   			System.out.println("Client n'existe pas");
		    	   			System.out.println("---------------------------------------------");
		    	   			rechercheMenu();
		    	   			
		    	   		}
		    	   		break;
		   			case 2:
		   				System.out.println("---------------------------------------------");
		   				System.out.println("Entrer nom du client a afficher");
		    	   		String idCl1 = clavier.nextLine();
		    	   		List<Client> c2 = chercherClientParNom(idCl1);
		    	   		if(c2 != null) {
		    	   			for(Client c : c2) {
		    	   				System.out.println(c.toString());
		    	   			}
		    	   			menu();
		    	   		}
		    	   		else {
		    	   			System.out.println("Client n'existe pas");
		    	   			System.out.println("---------------------------------------------");
		    	   			rechercheMenu();
		    	   			
		    	   		}
		    	   		break;
			       	case 3: 
			       		System.out.println("---------------------------------------------");
		   				System.out.println("Entrer prenom du client a afficher");
		    	   		String idCl2 = clavier.nextLine();
		    	   		List<Client> c3 = chercherClientParPrénom(idCl2);
		    	   		if(c3 != null) {
		    	   			for(Client c : c3) {
		    	   				System.out.println(c.toString());
		    	   			}
		    	   			menu();
		    	   		}
		    	   		else {
		    	   			System.out.println("Client n'existe pas");
		    	   			System.out.println("---------------------------------------------");
		    	   			rechercheMenu();
		    	   		}
		    	   		break;
			       	case 4: 
			       		System.out.println("---------------------------------------------");
		    	   		System.out.println("Entrer CIN du client a afficher");
		    	   		String idCl3 = clavier.nextLine();
		    	   		Client c4 = chercherClientParCin(idCl3);
		    	   		if(c4 != null) {
		    	   			System.out.println(c4.toString());
		    	   			menu();
		    	   		}
		    	   		else {
		    	   			System.out.println("Client n'existe pas");
		    	   			System.out.println("---------------------------------------------");
		    	   			rechercheMenu();	
		    	   		}	
		    	   		break;
			       	case 5:    
			       		System.out.println("---------------------------------------------");
		    	   		System.out.println("Entrer email du client a afficher");
		    	   		String idCl4 = clavier.nextLine();
		    	   		Client c5 = chercherClientParEmail(idCl4);
		    	   		if(c5 != null) {
		    	   			System.out.println(c5.toString());
		    	   			menu();
		    	   		}
		    	   		else {
		    	   			System.out.println("Client n'existe pas");
		    	   			System.out.println("---------------------------------------------");
		    	   			rechercheMenu();
		    	   		}
		    	   		break;
		   			case 0: 
		   				menu();
		   				break;
		   			default:
			 	           System.out.println("Choix incorrect");
			 	           rechercheMenu();
			 	           break;
			    }
	       case 2:
	    	   	System.out.println("------------------ Recherche Compte -------------");
		   		System.out.println("Taper [1] pour chercher compte par id");
		   		System.out.println("Taper [2] pour chercher compte par solde");
		   		System.out.println("Taper [3] pour chercher compte par date creation");
		   		System.out.println("Taper [4] pour chercher compte par proprietaire");
		   		System.out.println("Taper [0] pour revenir en arriere");
		   		System.out.println("-------------------------------------------------");
		   		int choixCo = Integer.parseInt(clavier.nextLine());
		   		switch(choixCo){
			       case 1: 
			    	   	System.out.println("---------------------------------------------");
		    	   		System.out.println("Entrer id du compte a afficher");
		    	   		String idCl = clavier.nextLine();
		    	   		Compte c1 = chercherCompteParId(idCl);
		    	   		if(c1 != null) {
		    	   			System.out.println(c1.toString());
		    	   			menu();
		    	   		}
		    	   		else {
		    	   			System.out.println("Compte n'existe pas");
		    	   			System.out.println("---------------------------------------------");
		    	   			rechercheMenu();
		    	   		}
		    	   		break;
			       case 2: 
			    	   	System.out.println("---------------------------------------------");
		    	   		System.out.println("Entrer Solde du compte a afficher");
		    	   		Double idCl1 = Double.parseDouble(clavier.nextLine());
		    	   		List<Compte> c2 = chercherCompteParSolde(idCl1);
		    	   		if(c2 != null) {
		    	   			for(Compte c : c2)
		    	   				System.out.println(c.toString());
		    	   			menu();
		    	   		}
		    	   		else {
		    	   			System.out.println("Compte n'existe pas");
		    	   			System.out.println("---------------------------------------------");
		    	   			rechercheMenu();
		    	   		}
		    	   		break;
			       case 3: 
			    	   menu();
			    	   break;
			       case 4: 
			    	   System.out.println("Entrer id du compte a afficher");
		    	   		Long idCl3 = Long.parseLong(clavier.nextLine());
		    	   		Client cl = chercherClientParId(idCl3);
		    	   		List<Compte> c4 = chercherCompteParPropriétaire(cl);
		    	   		if(c4 != null) {
		    	   			for(Compte c : c4)
		    	   				System.out.println(c.toString());
		    	   			menu();
		    	   		}
		    	   		else {
		    	   			System.out.println("Compte n'existe pas");
		    	   			System.out.println("---------------------------------------------");
		    	   			rechercheMenu();
		    	   		}
		    	   		break;
			       case 0: 
			    	   menu();
			       default:
		 	           System.out.println("Choix incorrect");
		 	           rechercheMenu();
		 	           break;
			    }
		}
	}
	
	public void menu() {
		ServiceIHMAdmin menu = new ServiceIHMAdmin(getBanque());
		int choix = menu.menuGlobal();
		
		switch(choix){
	       case 1: 
	           int choixCrud = menu.menuCRUD();
	           switch(choixCrud){
	    	   	case 1:
	    	   		int choixAjouter = menu.menuAjout();
	    	   		switch(choixAjouter){
		    	   	case 1:
		    	   		List<Client> clients = getBanque().getClientsDeBanque();
		    	   		Client c2 = nouveauClient();
		    	   		clients.add(c2);
		    	   		Banque banque = getBanque();
		    	   		banque.setClientsDeBanque(clients);
		    	   		setBanque(banque);
		    	   		System.out.println(c2.toString());
		    	   		menu();
		    	   		break;
		    	   	case 2:
		    	   		List<Client> clients1 = getBanque().getClientsDeBanque();
		    	   		Client c3 = nouveauCompteClientExistant();
		    	   		if(c3 != null) {
		    	   			clients1.add(c3);
			    	   		Banque banque1 = getBanque();
			    	   		banque1.setClientsDeBanque(clients1);
			    	   		setBanque(banque1);
			    	   		System.out.println(c3.toString());
		    	   		}else
		    	   			System.out.println("compte n'existe pas");
		    	   		menu();
		    	   		break;
		    	   	case 0 :
		    	   		menu();
		    	   		break;
		    	   	default:
		 	           System.out.println("Choix incorrect");
		 	           menu();
		 	           break;
		    	   }
	    	   		menu();
	    	   	case 2:
	    	   		System.out.println("-----------Modifier  Client------------------");
	    	   		System.out.println("Entrer id du client a modifier");
	    	   		String idC = clavier.nextLine();
	    	   		Client c = modifierClient(idC);
	    	   		System.out.println(c.toString());
	    	   		System.out.println("---------------------------------------------");
	    	   		menu();
	    	   		break;
	    	   	case 3:
	    	   		System.out.println("-----------Supprimer Client------------------");
	    	   		System.out.println("Entrer id du client a supprimer");
	    	   		Long id = Long.parseLong(clavier.nextLine());
	    	   		boolean b = supprimerClient(id);
	    	   		if(b)
	    	   			System.out.println("Client supprime");
	    	   		else
	    	   			System.out.println("Client non supprime");
	    	   		System.out.println("---------------------------------------------");
	    	   		menu();
	    	   		break;
	    	   	case 4:
	    	   		rechercheMenu();
	    	   		break;
	    	   	case 0 :
	    	   		menu();
	    	   		break;
	    	   	default:
	 	           System.out.println("Choix incorrect");
	 	           break;
	    	   }
	           menu();
	       case 2:
	    	   System.out.print(getBanque().toString());
	    	   menu();
	       case 3:
	    	   int choixTri = menu.menuTrie();
	    	   switch(choixTri){
	    	   	case 1:
	    	   		List<Client> cN = trierClientParNom();
	    	   		for(Client cn : cN)
	    	   			System.out.println(cn.toString());
	    	   		menu();
	    	   		break;
	    	   	case 2:
	    	   		List<Client> cC = trierClientParCin();
	    	   		for(Client cc : cC)
	    	   			System.out.println(cc.toString());
	    	   		menu();
	    	   		break;
	    	   	case 3:
	    	   		List<Client> cE = trierClientParEmail();
	    	   		for(Client ce : cE)
	    	   			System.out.println(ce.toString());
	    	   		menu();
	    	   		break;
	    	   	case 4: 
	    	   		List<Client> cS = trierClientParSoldeCompte();
	    	   		for(Client cs : cS)
	    	   			System.out.println(cs.toString());
	    	   		menu();
	    	   		break;
	    	   	case 5:
	    	   		List<Compte> ccS = trierComptesParSolde();
	    	   		for(Compte ccs : ccS)
	    	   			System.out.println(ccs.toString());
	    	   		menu();
	    	   		break;
	    	   	case 6:
	    	   		List<Compte> ccD = trierComptesParDateDeCreation();
	    	   		for(Compte ccd : ccD)
	    	   			System.out.println(ccd.toString());
	    	   		menu();
	    	   		break;
	    	   	case 7:
	    	   		List<Compte> ccNP = trierComptesParNomPropriétaire();
	    	   		for(Compte ccnp : ccNP)
	    	   			System.out.println(ccnp.toString());
	    	   		menu();
	    	   		break;
	    	   	case 0 :
	    	   		menu();
	    	   		break;
	    	   	default:
	 	           System.out.println("Choix incorrect");
	 	           break;
	    	   }
	    	   menu();
	    	   break;
	       case 4:
	    	   System.out.println("----------- Tableau de bord -------------");
	    	   System.out.println(calculerEtAfficherStatistiques().toString());
	    	   menu();
	    	   break;
	       case 0:
	    	   Deconnexion deconnexion = new Deconnexion();
	    	   deconnexion.SeDéconnecter();
	    	   break;
	       default:
	           System.out.println("Choix incorrect");
	           menu();
	           break;
	   }
	}
}
