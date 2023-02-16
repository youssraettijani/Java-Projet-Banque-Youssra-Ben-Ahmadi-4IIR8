package metier.authentification;

import metier.InteractiveConsole;
import metier.admin.ServiceAdmin;
import metier.clients.ServicesClient;
import presentation.modele.Admin;
import presentation.modele.Banque;
import presentation.modele.Client;

public class ServiceAuth implements IAuth, InteractiveConsole{
	
	private Banque banque;
	public ServiceAuth(Banque banque) {this.banque = banque;}
	
	public Banque getBanque() {return banque;}
	public void setBanque(Banque banque) {this.banque = banque;}
	
	public boolean loginNbrChars(String login) {
		if(login.length() >= 4)
			return true;
		return false;
	}
	@Override
	public void seConnecter() {
		boolean j = false;
		System.out.println("-----------Connexion --------------");
		System.out.print("Login : ");
		String login = clavier.nextLine();
		System.out.println("-------------------------------------------");
		while(loginNbrChars(login) == false) {
			System.out.print("LOGIN incorrecte !! Entrer le login a nouveau (contenant 4 chars ou plus) : ");
			login = clavier.nextLine();
		}
		if(login.equals(Admin.getInstance().getLogin()) ) {
			System.out.print("Mot de passe : ");
			String password = clavier.nextLine();
			while(password.equals(Admin.getInstance().getMotDePasse()) == false) {
				System.out.print("MDP incorrecte !! Entrer le mot de passe a nouveau : ");
				password = clavier.nextLine();
			}
			j =true;
			System.out.println("----------------------------------------------");
			System.out.println("Hello ADMIN " + Admin.getInstance().getNomComplet());
			System.out.println("----------------------------------------------");
			ServiceAdmin serviceAdmin = new ServiceAdmin(banque);
			serviceAdmin.menu();
			
		}else {
			for(Client client : banque.getClientsDeBanque()) {
				if(client.getLogin().equals(login)) {
					System.out.print("Mot de passe : ");
					String password = clavier.nextLine();
					while(password.equals(client.getMotDePasse()) == false) {
						System.out.print("MDP incorrecte !! Entrer le mot de passe a nouveau : ");
						password = clavier.nextLine();
					}
					j = true;
					System.out.println("----------------------------------------------");
					System.out.println("Hello Client " + client.getNomComplet());
					System.out.println("----------------------------------------------");
					ServicesClient serviceClient = new ServicesClient(client);
					serviceClient.menuDepart();
				}
			}
		}
		if(j == false) {
			System.out.println("Le login n'existe pas !!!");
			seConnecter();
		}
	}
	@Override
	public void SeDéconnecter() {
		System.out.println("Au revoir");
		fermerClavier();
	}

}
