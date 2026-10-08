# PracticaJPA - Usuarios

Aplicación web Java con **JPA + JSP + Servlets** para gestionar usuarios (CRUD completo) con arquitectura MVC y patrón DAO.

## 📋 Características

- Alta, consulta, edición y eliminación de usuarios
- Campos: `id`, `nombre`, `correo` (el correo es único)
- Validación de campos obligatorios y aviso de correo duplicado
- Eliminación por `POST` con confirmación
- Patrón Post-Redirect-Get al guardar
- Salida escapada con `<c:out>` para evitar XSS

## 🛠️ Tecnologías

| Capa | Tecnología |
|---|---|
| Lenguaje | Java 21 |
| Build | Maven (`war`) |
| Persistencia | JPA (Jakarta Persistence 3.1) con Hibernate 6.4 |
| Base de datos | MySQL (XAMPP) |
| Vista | JSP + JSTL |
| Controlador | Servlets (Jakarta Servlet) |
| Servidor | Apache Tomcat 10.1+ |

## 📁 Estructura

```
src/main
├── java
│   ├── controlador/   UsuarioServlet, AppListener
│   ├── dao/           UsuarioDao, UsuarioDAOImpl
│   ├── modelo/        Usuario (entidad)
│   └── util/          JPAUtil
├── resources/META-INF/persistence.xml
└── webapp
    ├── index.jsp
    └── WEB-INF/views/ usuarios.jsp, formulario.jsp
```

## ▶️ Cómo ejecutarlo

1. Inicia **MySQL** desde XAMPP y crea la base de datos `mi_base_datos`.
2. Revisa usuario y contraseña en `src/main/resources/META-INF/persistence.xml`.
3. Abre el proyecto en NetBeans y registra un servidor **Apache Tomcat 10.1 o superior**.
4. **Clean and Build** y luego **Run**.
5. Entra a `http://localhost:8080/sistemadao/`.

Hibernate crea la tabla `usuarios` automáticamente (`hbm2ddl.auto=update`).

## 🗄️ Tabla generada

| Columna | Tipo | Restricciones |
|---|---|---|
| id | INT | PK, autoincrement |
| nombre | VARCHAR(100) | NOT NULL |
| correo | VARCHAR(100) | NOT NULL, UNIQUE |
