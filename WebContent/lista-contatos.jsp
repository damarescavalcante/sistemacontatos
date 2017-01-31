<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="dao" class="com.sistemacontatos.dao.ContatoDAO" />
<html>
<head>
	<title>Lista de Contatos</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<table class="table table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Endereço</th>
				<th>Data de Nascimento</th>
			</tr>
			<c:forEach var="contato" items="${dao.listarContatos}">
				<tr>
					<td>${contato.nome}</td>
					<td><c:if test="${not empty contato.email}">
							<a href="mailto:${contato.email}">${contato.email}</a>
						</c:if> <c:if test="${empty contato.email}">
								E-mail não informado.
							</c:if></td>
					<td>${contato.endereco}</td>
					<td><fmt:formatDate value="${contato.dataNascimento.time}"
							pattern="dd/MM/yyyy" /></td>
				</tr>
			</c:forEach>
		</table>	
	
	</div>
</body>
</html>