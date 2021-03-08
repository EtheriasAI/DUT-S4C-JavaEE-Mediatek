package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Mediatek;
import mediatek2021.User;
import mediatek2021.Utilisateur;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//on utilise la method post dans la balise form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd"); 
        
        Utilisateur b = new User("test","test");
        
        b = Mediatek.getInstance().getUser(login,pwd);
        
        String rep;
        if(b.login()!=null)
        	rep="Bonjour " + login + " votre code de CB est " + pwd + "!";
        else
        	rep="login invalide!";
        response.getOutputStream().println(rep);
        //this.getServletContext().getRequestDispatcher("/portail.jsp").forward(request,response);
        
    }
}

