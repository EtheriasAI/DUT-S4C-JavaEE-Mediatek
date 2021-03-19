<%@ page import="java.util.ArrayList" %>
<%@ page import="mediatek2021.Document" %>

<!DOCTYPE HTML>

<html>
<head>
<title>Mediatek2021</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>Bienvenue a toi <%= request.getAttribute("login") %></h1>

<p>Tu peux soit ajouter ou supprimer un document</p>

<a href="ajout_document.jsp">Ajouter un document</a>
<a href="retirer_document.jsp">Retirer un document</a>

<p>Voici le catalogue des documents</p>

<% ArrayList<Document> CDlist = (ArrayList<Document>) request.getAttribute("CD"); %>
<% ArrayList<Document> DVDlist = (ArrayList<Document>) request.getAttribute("DVD"); %>
<% ArrayList<Document> Livrelist = (ArrayList<Document>) request.getAttribute("Livre"); %>
<table>
	<tr>
		<th style="background-color:#858585;" colspan="3">CD</th>
	
    <% for(Document a : CDlist){
		Object[] tab = a.data(); %>
		<tr>
		<td> <%= tab[1] %> </td>
		<td> <%= tab[2] %> </td>
        </tr>
        <% } %>
	</tr>
</table>
<table>
	<tr>
		<th style="background-color:#858585;" colspan="3">DVD</th>
	
    <% for(Document b : DVDlist){
		Object[] tab = b.data(); %>
		<tr>
		<td> <%= tab[1] %> </td>
		<td> <%= tab[2] %> </td>
        </tr>
        <% } %>
	</tr>
</table>
<table>
	<tr>
		<th style="background-color:#858585;" colspan="3">Livre</th>
	
    <% for(Document c : Livrelist){
		Object[] tab = c.data(); %>
		<tr>
		<td> <%= tab[1] %> </td>
		<td> <%= tab[2] %> </td>
        </tr>
        <% } %>
	</tr>
</table>

</body>

</html>