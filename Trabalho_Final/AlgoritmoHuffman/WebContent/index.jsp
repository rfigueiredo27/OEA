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
		<h1>Algoritmo de Huffman</h1>
		<p>Essa aplicação tem o objetivo de demostrar o funcionamento do Algoritmo de Huffman.</p>
		<p>Para começar digite uma palavra ou um texto no campo abaixo:</p>
		<form action="huffman.jsp" target="divbusca" name="huffman" id="huffman" method="post">
		<textarea name="palavra" id="palavra" rows="5" cols="200" style="width: 100%; margin-bottom: 20px;" required></textarea>
		<input type="submit" class="btn btn-success" value="Iniciar o Algoritmo"></input>
		</form>
		
		<div>
 			<jsp:include page="huffman.jsp" /> 
		</div>
</div>
</body>
</html>