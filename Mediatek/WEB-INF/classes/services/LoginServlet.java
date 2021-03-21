package services;

/**
 * Classe pour gerer la connexion
 * et pour lancer le portail
 * 
 * @author DOGHRI FARAH
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//on utilise la method post dans la balise form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*on recupere les donnees du formulaire*/
    	String login = request.getParameter("login");
        String pwd = request.getParameter("pwd"); 
        /*on recupere l'utilisateur*/
        Utilisateur b = Mediatek.getInstance().getUser(login,pwd);
        /*on envoie le login de l'utilisateur*/
        request.setAttribute("login", b.login());
        /*on recupere les catalogues*/
        ArrayList<Document>docCD=(ArrayList<Document>) Mediatek.getInstance().catalogue(0);
        ArrayList<Document>docDVD=(ArrayList<Document>) Mediatek.getInstance().catalogue(1);
        ArrayList<Document>docLivre=(ArrayList<Document>) Mediatek.getInstance().catalogue(2);
        /*on envoie les catalogues*/
        request.setAttribute("CD", docCD);
        request.setAttribute("DVD", docDVD);
        request.setAttribute("Livre", docLivre);
        /*on verifie si le login et le mot de passe sont bon pour pouvoir se connecter
         * Si ils sont bons l'utilisateur peut acceder a l'application
         * sinon un message d'erreur s'affiche
         */
        if(b.login().equals(login)&&b.password().equals(pwd))
        	this.getServletContext().getRequestDispatcher("/portail.jsp").forward(request,response);
        else {
        	request.setAttribute("error","le login et ou mot de passe n'est pas bon");
        	this.getServletContext().getRequestDispatcher("/error.jsp").forward(request,response);        	
        }
    }
}

