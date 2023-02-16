package metier;
import presentation.modele.*;
import dao.ClientDao;

public interface Verifiable {
    default boolean isNumeric(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    default boolean isDecimal(String value){
        try {
            Double.parseDouble(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    default boolean isAdmin(String login,String mdp){
        Admin admin = Admin.getInstance();
        return login.equals(admin.getLogin()) && mdp.equals(admin.getMotDePasse());
    }

    default Client isClient(String login,String mdp){
        ClientDao clientDAO = new ClientDao();
        for(Client client : clientDAO.findAll())
            if(client.getLogin().equals(login) && client.getMotDePasse().equals(mdp))
                return client;
        return null;
    }
}
