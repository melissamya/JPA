<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Usuarios</title>
    <style>
        body { font-family: system-ui, sans-serif; max-width: 800px; margin: 2rem auto; padding: 0 1rem; color: #222; }
        h1 { margin-bottom: .5rem; }
        table { width: 100%; border-collapse: collapse; margin-top: 1rem; }
        th, td { padding: .6rem .8rem; text-align: left; border-bottom: 1px solid #ddd; }
        th { background: #f3f4f6; }
        .btn { display: inline-block; padding: .4rem .8rem; border: 0; border-radius: 6px;
               background: #2563eb; color: #fff; text-decoration: none; cursor: pointer; font-size: .9rem; }
        .btn-edit { background: #d97706; }
        .btn-del { background: #dc2626; }
        .msg { padding: .7rem 1rem; border-radius: 6px; margin: 1rem 0; }
        .ok { background: #dcfce7; color: #166534; }
        .error { background: #fee2e2; color: #991b1b; }
        form.inline { display: inline; }
        .vacio { text-align: center; color: #666; padding: 1.5rem; }
    </style>
</head>
<body>
    <h1>Usuarios</h1>
    <a class="btn" href="${pageContext.request.contextPath}/usuarios?accion=nuevo">+ Nuevo usuario</a>

    <c:if test="${param.ok == 'guardado'}">
        <div class="msg ok">Usuario guardado correctamente.</div>
    </c:if>
    <c:if test="${param.ok == 'eliminado'}">
        <div class="msg ok">Usuario eliminado.</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="msg error"><c:out value="${error}"/></div>
    </c:if>

    <table>
        <thead>
            <tr><th>ID</th><th>Nombre</th><th>Correo</th><th>Acciones</th></tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${usuarios}">
                <tr>
                    <td>${u.id}</td>
                    <td><c:out value="${u.nombre}"/></td>
                    <td><c:out value="${u.correo}"/></td>
                    <td>
                        <a class="btn btn-edit"
                           href="${pageContext.request.contextPath}/usuarios?accion=editar&id=${u.id}">Editar</a>

                        <form class="inline" method="post"
                              action="${pageContext.request.contextPath}/usuarios"
                              onsubmit="return confirm('¿Eliminar a este usuario?');">
                            <input type="hidden" name="accion" value="eliminar">
                            <input type="hidden" name="id" value="${u.id}">
                            <button type="submit" class="btn btn-del">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty usuarios}">
                <tr><td colspan="4" class="vacio">No hay usuarios registrados.</td></tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>
