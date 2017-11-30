<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.cefetrj.trabalhoFinal.servico.No"%>
<%@page import="br.cefetrj.trabalhoFinal.servico.Folha"%>
<%@page import="br.cefetrj.trabalhoFinal.entidade.Huffman"%>
<%@page import="br.cefetrj.trabalhoFinal.servico.Arvore"%>
<%@page import="br.cefetrj.trabalhoFinal.servico.AlgoritmoHuffman"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/tether.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Algoritmo de Huffman</title>
</head>
<body>
<div class="container">

<%
String palavra = "";
if(request.getParameter("palavra") == null)
	out.print("<br><p>Aguardando a inicialização do Algoritmo...</p>");
else
{

	palavra = request.getParameter("palavra");
	
	int[] frequencia_caracteres = new int[256];
    for (char c : palavra.toCharArray())
    	frequencia_caracteres[c]++;
    
    AlgoritmoHuffman hf = new AlgoritmoHuffman();
    
    Arvore arvore = hf.construtorArvore(frequencia_caracteres);
	
    out.print("<h2>Representação do Algoritmo de Huffman</h2>");
    out.print("<table class='table table-bordered table-striped table-hover'><thead>");
    out.print("<th>Símbolos</th>");
    out.print("<th>Repetições</th>");
    out.print("<th>Código</th>");
    out.print("</thead><tbody>");

    List<String> lista = hf.getLista(arvore, palavra);
 	
 	for (String s : lista ) {
 	      String[] texto = s.split("_");
 	      String simbolo = texto[0];
 	      String repet = texto[1];
 	      String codigo = texto[2];
 	      
 	      %>
 	        <tr>
 	          <td><% out.print(simbolo); %></td>
 	          <td><% out.print(repet); %></td> 
 	          <td><% out.print(codigo); %></td>  
 	          
 	        </tr>
 	      <%
 	      }
 	out.print("</tbody></table>");
    
    String codificar = hf.codificar(arvore,palavra);
    out.print("<h3>TEXTO COMPACTADO</h3>");
    out.print("<p>"+codificar+"<p>");
    
    out.print("<br><h3>TEXTO DECODIFICADO</h3>");
    out.print("<p>"+hf.decodificar(arvore,codificar)+"<p>");
    out.print("<a href=\"index.jsp\" class=\"btn btn-danger\">Voltar</a>");
}

%>

</div>
</body>
</html>