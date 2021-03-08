package services;
import java.sql.*;

/* exercices 1 et 2 du td JDBC
 * les pilotes de la base 
 * exercice 1 : JDBC-ODBC-Oracle
 * exercice 2 : JDBC-Oracle
 */

public class TestJDBC {

	public static void main(java.lang.String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection cnx = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TEST","TEST");
		String reqSQL = "SELECT loginu, pwdu FROM utilisateur";
		Statement stReq = cnx.createStatement();
		ResultSet res = stReq.executeQuery(reqSQL);			
		while (res.next()) {
			System.out.println(res.getString("loginu")+ " " +res.getString("pwdu"));
		}

		res.close(); stReq.close(); cnx.close();
	}
}
