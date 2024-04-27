<%-- 
    Document   : listarAuditoria
    Created on : Nov 16, 2023, 10:12:04 AM
    Author     : usuario
--%>

<%@page import="Modelo.ClsModeloAuditoria"%>
<%@page import="Modelo.ClsModeloAuditoria"%>
<%@page import="ModeloDAO.ClsModeloDaoAuditoria"%>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- tuPagina.jsp -->
<html>
<head>
    <title>Listado de Auditoría</title>
        <style>
        .card {
            width: 400px;
            margin: 20px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            background-color: #fff;
        }
    </style>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <h1>Listado de Auditoría</h1><br>

    <table border="1">
        <tr>
            <th>ID Registro</th>
            <th>ID Admin</th>
            <th>Acción</th>
            <th>Fecha</th>
        </tr>

        <%
            ClsModeloDaoAuditoria daoAuditoria = new ClsModeloDaoAuditoria();
            List<ClsModeloAuditoria> listaAuditoria = daoAuditoria.obtenerAuditoria();

            for (ClsModeloAuditoria auditoria : listaAuditoria) {
        %>
                <tr>
                    <td><%= auditoria.getIdRegistro() %></td>
                    <td><%= auditoria.getFKidAdmin() %></td>
                    <td><%= auditoria.getAccion() %></td>
                    <td><%= auditoria.getFecha() %></td>
                </tr>
        <%
            }
        %>
    </table>
</body>
</html>
