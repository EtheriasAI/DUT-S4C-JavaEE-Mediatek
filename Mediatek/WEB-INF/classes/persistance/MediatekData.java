package persistance;

import java.util.ArrayList;
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
    /*Connection a la bd*/
    private Connection cnx;
    
    private MediatekData() throws Exception {
    	/*initialisation de la connexion a la bd*/
    	Class.forName("oracle.jdbc.OracleDriver");
		cnx = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TEST","TEST");
    }

    // renvoie la liste de tous les documents de la bibliotheque
    @Override
    public List<Document> catalogue(int type) {
    	//3type de Document
    	List<Document> test= new ArrayList<Document>();
    	/*requete SQL pour rechercher les elements portant le type*/
    	String reqSQL = "SELECT typeD,idD, nomD FROM document order by idD";
		Statement stReq;
		try {
			stReq = cnx.createStatement();		//prepare c'est mieux donc a voir
			ResultSet res = stReq.executeQuery(reqSQL);
			while (res.next()) {
				/*si le type correspond on recupere les donnees et on l'ajoute au catalogue*/
				int t =Integer.parseInt(res.getString("typeD"));
				if(t==type)	{
					String mesArgs[]= new String[3];
					mesArgs[0]=Integer.toString(type);
					mesArgs[1]=res.getString("idD");
					mesArgs[2]=res.getString("nomD");
					test.add(new Doc(mesArgs));
					}
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		return test;
    }

    // va recuperer le User dans la BD et le renvoie
    // si pas trouve, renvoie null
    @Override
    public Utilisateur getUser(String login, String password) {
    	/*requete SQL pour rechercher l'utilisateur portant le login et le password*/
    	String reqSQL = "SELECT loginu, pwdu FROM utilisateur";
		Statement stReq;
		try {
			stReq = cnx.createStatement();		//prepare c'est mieux donc a voir
			ResultSet res = stReq.executeQuery(reqSQL);
			while (res.next()) {
				if(res.getString("loginu").equals(login)&&res.getString("pwdu").equals(password))
					return new User(login,password);
			}
		} catch (SQLException e) {e.printStackTrace();}
		return new User("null","null");
    	
    }

    // va recuperer le document de numero numDocument dans la BD
    // et le renvoie
    // si pas trouve, renvoie null
    @Override
    public Document getDocument(int numDocument) {
    	/*requete SQL pour recherhcer le document portant l'id*/
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
    	/*requete SQL pour ajouter une ligne*/
        String reqSQL = "INSERT INTO document (typeD, idD, nomD) VALUES ("+type+","+ args[0] +",'"+args[1]+"')";
		Statement stReq;
		try {
			stReq = cnx.createStatement();		
			ResultSet res = stReq.executeQuery(reqSQL);
		} catch (SQLException e) { throw new NewDocException(e.getLocalizedMessage());}
    }

	// supprime un document - exception a definir
	@Override
    public void suppressDoc(int numDoc) throws SuppressException {
		/*requete SQL pour supprimer une ligne*/
    	String reqSQL = "delete from document where idD =" + numDoc;
		Statement stReq;
		try {
			stReq = cnx.createStatement();		
			ResultSet res = stReq.executeQuery(reqSQL);
		} catch (SQLException e) {throw new SuppressException(e.getLocalizedMessage());}
    }

}