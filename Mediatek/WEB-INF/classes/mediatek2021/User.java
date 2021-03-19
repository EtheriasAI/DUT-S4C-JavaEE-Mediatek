package mediatek2021;

public class User implements Utilisateur {
	
	/*login de l'utilisateur*/
	private String login;
	/*tableau du mot de passe de l'utilisateur*/
	private String pwd;
	/*tableau des donnees suplementaires de l'utilisateur*/
	private Object[]data;
	
	public User(String l, String p) {
		login=l;
		pwd=p;
	}
	/*@return le login de l'utilisateur*/
	@Override
	public String login() {
		return login;
	}
	/*@return le mot de passe de l'utilisateur*/
	@Override
	public String password() {
		return pwd;
	}
	/*@return le tableau des donnees suplementaires de l'utilisateur*/
	@Override
	public Object[] data() {
		return data;
	}

}
