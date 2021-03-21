package mediatek2021;

/**
 * Classe implementant l'interface Document
 * 
 * @author DOGHRI FARAH
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

public class Doc implements Document{
	
	/*tableau des donnees du document*/
	private Object[] data;

	public Doc(Object data[]) {
		this.data=data;
	}
	/*@return le tableau des donnees du document*/
	@Override
	public Object[] data() {
		return data;
	}
}