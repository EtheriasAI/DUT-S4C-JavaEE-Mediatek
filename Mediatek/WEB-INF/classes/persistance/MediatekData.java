package persistance;

import java.util.List;
import java.sql.*;

import mediatek2021.*;

// classe mono-instance : l'unique instance est connue de la bibliotheque
// via une injection de dependance dans son bloc static

public class MediatekData implements PersistentMediatek {
// Jean-Francois Brette 01/01/2018
    static {
        // injection dynamique de la dependance dans le package stable mediatek2021
    	try {
			Mediatek.getInstance().setData(new MediatekData());
		} catch (Exception e) {	e.printStackTrace();}
    }

    private Connection cnx;
    
    private MediatekData() throws Exception {
    	Class.forName("oracle.jdbc.OracleDriver");
		cnx = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TEST","TEST");
		
    }

    // renvoie la liste de tous les documents de la bibliotheque
    @Override
    public List<Document> catalogue(int type) {
    	//3type de Document
        return null;
    }

    // va recuperer le User dans la BD et le renvoie
    // si pas trouve, renvoie null
    @Override
    public Utilisateur getUser(String login, String password) {
    	String reqSQL = "SELECT loginu, pwdu FROM utilisateur";
		Statement stReq;
		Utilisateur a = null;
		try {
			stReq = cnx.createStatement();
			ResultSet res = stReq.executeQuery(reqSQL);
			while (res.next()) {
				if(res.getString("loginu").contentEquals(login)&&res.getString("pwdu").contentEquals(password))
					return a;
			}
		} catch (SQLException e) {e.printStackTrace();}
		return null;
    	
    }

    // va recuperer le document de numero numDocument dans la BD
    // et le renvoie
    // si pas trouve, renvoie null
    @Override
    public Document getDocument(int numDocument) {
    	String reqSQL = "SELECT numd FROM document";
		Statement stReq;
		try {
			stReq = cnx.createStatement();
			ResultSet res = stReq.executeQuery(reqSQL);			
			while (res.next()) {
				if(res.getInt("numd")==numDocument)
					return new Doc(null);
			}
		} catch (SQLException e) {e.printStackTrace();}
		return new Doc(null);
    }

    // ajoute un nouveau document - exception a definir
    @Override
    public void newDocument(int type, Object... args) throws NewDocException {
        // args[0] -> le titre
        // args [1] --> l'auteur
        // etc en fonction du type et des infos optionnelles
    }

    // supprime un document - exception a definir
    @Override
    public void suppressDoc(int numDoc) throws SuppressException {
        
    }

}