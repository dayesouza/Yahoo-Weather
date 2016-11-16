<%-- 
    Document   : index
    Created on : 16/11/2016, 10:58:49
    Author     : dayennesouza
--%>
<%@page import="br.com.pasta.TempoBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
TempoBean meuBean = (TempoBean)request.getAttribute("chaveEndereco");
if( meuBean != null ) {
	if( meuBean.getResultado() != null) {
		
	}
} else {
	meuBean = new TempoBean();
} 
%>
<%
String action = request.getParameter("action");
%>
        <h1>Hello World!</h1>
        <form name="f1" action="" method="POST">
        <input type="text" name="campoLocal">
        <select name="campoUnit">
            <option value="C">ºC</option>
            <option value="F">ºF</option>
        </select>
        <input type="button" name="btnGravar" value="OK" onClick="buscar()">
        </form>
        
        <div>
            <p><%=meuBean.getResultado()%></p>
        </div>
        <script>
            function buscar(){
                document.f1.action="TempoServlet?action=buscar";
                document.f1.submit();
            }
            
        </script>
    </body>
</html>
