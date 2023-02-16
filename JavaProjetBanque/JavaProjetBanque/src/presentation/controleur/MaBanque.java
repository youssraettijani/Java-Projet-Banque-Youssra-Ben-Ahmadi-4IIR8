package presentation.controleur;

import metier.authentification.ServiceAuth;
import presentation.modele.Banque;

public class MaBanque {
        public static ServiceAuth loginService;

        public static void main(String[] args) {

                Banque maBanque = new Banque("BP", "Hassan Rabat", "212535224433", "bp@banquePop.ma");
                loginService = new ServiceAuth(maBanque);
                loginService.seConnecter();
        }
}
