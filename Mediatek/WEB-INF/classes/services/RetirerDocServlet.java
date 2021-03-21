package services;

/**
 * Classe pour retirer les documents
 * 
 * @author DOGHRI FARAH
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Mediatek;
import mediatek2021.SuppressException;

@WebServlet("/retirerDoc")
public class RetirerDocServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//on utilise la method post dans la balise form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*on recupere l'id du formulaire*/
    	String id = request.getParameter("idD"); 
        /*on supprime le document de la mediatek*/
        try {
			Mediatek.getInstance().suppressDoc(Integer.parseInt(id));
		} catch (SuppressException | NumberFormatException e) {
			/*lance l'excpetion*/
			request.setAttribute("error", e);
			this.getServletContext().getRequestDispatcher("/error.jsp").forward(request,response);
			}
        /*on affiche un message comme quoi le document a bien ete supprime*/
    	response.getOutputStream().println("Document supprime");         
    }
}