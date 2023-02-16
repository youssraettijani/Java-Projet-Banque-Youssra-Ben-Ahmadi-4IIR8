package metier.authentification;

import metier.InteractiveConsole;

public class Deconnexion implements IAuth, InteractiveConsole{

	public Deconnexion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void seConnecter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SeDéconnecter() {
		System.out.println("Au revoir");
		fermerClavier();
	}

}
