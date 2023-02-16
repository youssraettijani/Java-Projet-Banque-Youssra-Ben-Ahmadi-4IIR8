package presentation.modele;

import java.util.*;


public class Client extends Utilisateur implements Comparable<Client>{

    private String email, cin, tel;
    private Sexe sexe;
    private List<Compte> comptesClient;


    public String       getCin() {return cin;}
    public String       getTel() {return tel;}
    public String       getEmail() {return email;}
    public List<Compte> getComptesClient() {return comptesClient;}
    public Sexe getSexe() {return sexe;}
    public void setSexe(Sexe sexe) {this.sexe = sexe;}
    public void         setEmail(String email) {this.email = email;}
    public void         setTel(String tel) {this.tel = tel;}
    public void         setCin(String cin) {this.cin = cin;}
    public void         setComptesClient(List<Compte> comptesClient) {this.comptesClient = comptesClient;}

    public Client(){comptesClient = new ArrayList<>();}

    public Client(String login, String pass){
        super(login, pass, "Client");
        comptesClient = new ArrayList<>();
    }

    public Client(String login, String pass, String n, String p){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        comptesClient = new ArrayList<>();
    }
    public Client(String login, String pass, String n, String p, String mail, String cin, String tel, Sexe sexe){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        setTel(tel);
        setEmail(mail);
        setCin(cin);
        setSexe(sexe);
        comptesClient = new ArrayList<Compte>();
    }
    
    public Double maxSolde() {
    	Double soldeMax = 0.0;
    	for(Compte compte : comptesClient) {
    		if(compte.getSolde() > soldeMax)
    			soldeMax = compte.getSolde();
    	}
    	return soldeMax;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if ( obj instanceof Client) {
            return this.getId() == ((Client) obj).getId();
        }
        else return false;
    }

    @Override
    public String toString() {

        String      clientStr  = "------------------------------------------------------\n";
                    clientStr += "| Identifiant du Client     : "   + this.id        + "\n";
                    clientStr += "| Nom Complet               : "   + this.getNomComplet() + "\n" ;
                    clientStr += "| Adresse email             : "   + this.email     + "\n" ;
                    clientStr += "| Numéro téléphone          : "   + this.tel       + "\n" ;
                    clientStr += "| Numéro de CIN             : "   + this.cin       + "\n" ;
                    clientStr += "------------------------------------------------------\n";

        return clientStr;
    }
	@Override
	public int compareTo(Client client) {
		if(getNomComplet().compareTo(client.getNomComplet()) == 0)
			if(getCin().compareTo(client.getCin()) == 0)
				if(getEmail().compareTo(client.getEmail()) == 0)
					return maxSolde().compareTo(client.maxSolde());
				else
					return getEmail().compareTo(client.getEmail());
			else
				return getCin().compareTo(client.getCin());
		else 
			return getNomComplet().compareTo(client.getNomComplet());
	}


}
