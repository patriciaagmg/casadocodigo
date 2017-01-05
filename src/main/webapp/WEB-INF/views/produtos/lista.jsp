<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Livros de tecnologia!</title>
</head>
<body>

<div>${sucesso}</div>
<div>${falha}</div>
<div>${erro}</div>

<h1> <s:message code="listas.de.produtos"></s:message> </h1>

	<table>
		<tr>
			<td> <s:message code="titulo"/></td>
			<td><s:message code="descricao"/></td>
			<td><s:message code="paginas"/></td>
		</tr>
		
		<c:forEach items="${produtos }" var="produto">
			<tr>				
				<td>
					<a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build() }">
						${produto.titulo }
					</a></td>
				<td>${produto.descricao }</td>
				<td>${produto.paginas }</td>
			</tr>
		</c:forEach>
		
	
	</table>

</body>
</html>