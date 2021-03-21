package services;

/**
 * Classe pour gerer l'ajout de document
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
import mediatek2021.NewDocException;

@WebServlet("/ajoutDoc")
public class AjoutDocServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//on utilise la method post dans la balise form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*on recupere les donnees du formulaire*/
    	String type = request.getParameter("typeD");
        String id = request.getParameter("idD"); 
        String nom = request.getParameter("nomD");
        /*On met les donnees dans un tableau*/
        String tab[]= new String[2];
        tab[0]= id;
        tab[1]=nom;
        /*On retrouve le type de document de la liste deroulante*/
        int i=0;
        if(type.contentEquals("CD"))
        	i=0;
        else if(type.contentEquals("DVD"))
        	i=1;
        else if(type.contentEquals("Livre"))
        	i=2;
        /*on ajoute le document a la mediatek*/
        try {
			Mediatek.getInstance().newDocument(i, tab);
		} catch (NewDocException e) {
			/*lance l'excpetion*/
			request.setAttribute("error", e);
			this.getServletContext().getRequestDispatcher("/error.jsp").forward(request,response);
		}
        /*on affiche un message comme quoi le document a bien ete cree*/
    	response.getOutputStream().println("Document "+ nom + " de type " + type + " id: " + id +" ajoute");
    }
}