package services;

/**
 * Classe pour charger la classe MediatekData
 * 
 * @author DOGHRI FARAH
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value="/initializeResources", loadOnStartup=1)
public class Init extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/*initiliase la classe MediatekData pour mediatek2021.Mediatek*/
	@Override
	public void init() throws ServletException {
		 try {
			Class.forName("persistance.MediatekData");
			System.out.println("test");
		 } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}