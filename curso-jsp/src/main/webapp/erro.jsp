<%@ page language="java" contentType="text/html; charset=UTF-8"
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página que mostra os erros</title>
</head>
<body>
<h1>Mensagem de Erro, entre em contato com a equipa de suporte do sistema</h1>
<%
out.print(request.getAttribute("msg"));
%>
</body>
</html>