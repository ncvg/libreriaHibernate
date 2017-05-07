
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "DTD/xhtml1-strict.dtd">

<%@page import="java.util.List" %>
<%@page import="arquitecturaLibros.Libro"%>
<%Libro libro = Libro.buscarPorClave(request.getParameter("isbn"));%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
    <head>
        <title>Editar Libro</title>        
        <script type="text/javascript" src="./js/validacion.js"></script>
        <link rel="stylesheet" type="text/css" href="./css/formato.css" media="screen" />
    </head>
    <body>
        <div>
            
            <form id="formularioEdicion" action="SalvarLibros.do">
                <fieldset>
                    <legend> Formulario edición libro </legend>
                    <p>
                        <label for="isbn">ISBN:</label>
                        <input id="isbn" type="text" name="isbn" value="${libro.isbn}"/>
                    </p>
                    <p>
                        <label for="titulo">Titulo:</label>
                        <input id="titulo" type="text" name="titulo" value="${libro.titulo}"/>
                    </p>
                    <p>
                        <label for="categoria">Categoria:</label>
                        <select name="Categoria">
                            <c:forEach var="categoria" items="${listaDeCategorias}">
                                <option value="${categoria}">${categoria}</option>
                            </c:forEach>
                            
                        </select>
                    </p>
                    <p>
                        <input type="submit" value="Guardar"/>
                    </p>
                
                </fieldset>
            </form>
        
        </div>
    </body>
</html>

