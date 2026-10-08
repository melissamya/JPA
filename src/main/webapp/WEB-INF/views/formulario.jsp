<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:choose><c:when test="${usuario.id == 0}">Nuevo usuario</c:when><c:otherwise>Editar usuario</c:otherwise></c:choose></title>
    <style>
        body { font-family: system-ui, sans-serif; max-width: 480px; margin: 2rem auto; padding: 0 1rem; color: #222; }
        label { display: block; margin-top: 1rem; font-weight: 600; }
        input[type=text], input[type=email] { width: 100%; padding: .6rem; margin-top: .3rem;
               border: 1px solid #ccc; border-radius: 6px; box-sizing: border-box; font-size: 1rem; }
        .acciones { margin-top: 1.5rem; display: flex; gap: .6rem; }
        .btn { display: inline-block; padding: .55rem 1rem; border: 0; border-radius: 6px;
               background: #2563eb; color: #fff; text-decoration: none; cursor: pointer; font-size: .95rem; }
        .btn-sec { background: #6b7280; }
        .msg { padding: .7rem 1rem; border-radius: 6px; margin: 1rem 0; background: #fee2e2; color: #991b1b; }
    </style>
</head>
<body>
    <h1>
        <c:choose>
            <c:when test="${usuario.id == 0}">Nuevo usuario</c:when>
            <c:otherwise>Editar usuario #${usuario.id}</c:otherwise>
        </c:choose>
    </h1>

    <c:if test="${not empty error}">
        <div class="msg"><c:out value="${error}"/></div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/usuarios">
        <input type="hidden" name="accion" value="guardar">
        <input type="hidden" name="id" value="${usuario.id}">

        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre" maxlength="100" required
               value="<c:out value='${usuario.nombre}'/>">

        <label for="correo">Correo</label>
        <input type="email" id="correo" name="correo" maxlength="100" required
               value="<c:out value='${usuario.correo}'/>">

        <div class="acciones">
            <button type="submit" class="btn">Guardar</button>
            <a class="btn btn-sec" href="${pageContext.request.contextPath}/usuarios">Cancelar</a>
        </div>
    </form>
</body>
</html>
