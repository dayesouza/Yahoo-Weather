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
        <title>WeatherRedder</title>
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
        <h2>Descubra como está o tempo!</h2>
        <form name="f1" action="" method="POST">
        <input type="text" name="campoLocal">
        <select name="campoUnit">
            <option value="C">ºC</option>
            <option value="F">ºF</option>
        </select><br><br>
        <input type="button" name="btnGravar" value="Buscar" onClick="buscar()">
        </form>
        
        <div>
            <p>Data: <b><%=meuBean.getData()%></b></p>
            <p>Local: <%=meuBean.getLocal()%></p>
            <p>Situação: <%=meuBean.getTexto_situacao()%></p>
            <p style="color:<%=meuBean.getCor_temp()%>;">Temperatura: <%=meuBean.getTemperatura()%> <%=meuBean.getUnit()%> º</p>
            <div>
            <img src="imgs/<%=meuBean.getCod_situacao()%>" style="width: 100px;height: 100px">
            </div>
        </div>
        <script>
            function buscar(){
                document.f1.action="TempoServlet?action=buscar";
                document.f1.submit();
            }
            
        </script>
    </body>
</html>
