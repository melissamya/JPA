package controlador;

import dao.UsuarioDAOImpl;
import dao.UsuarioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;

/**
 * Controlador del CRUD de usuarios.
 *
 * GET  /usuarios                  -> lista
 * GET  /usuarios?accion=nuevo     -> formulario vacío
 * GET  /usuarios?accion=editar&id=3 -> formulario con datos
 * POST /usuarios (accion=guardar)  -> inserta o actualiza
 * POST /usuarios (accion=eliminar) -> elimina
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private static final String VISTA_LISTA = "/WEB-INF/views/usuarios.jsp";
    private static final String VISTA_FORM  = "/WEB-INF/views/formulario.jsp";

    private final UsuarioDao dao = new UsuarioDAOImpl();

    // ==========================
    // GET: mostrar vistas
    // ==========================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = req.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                req.setAttribute("usuario", new Usuario());
                req.getRequestDispatcher(VISTA_FORM).forward(req, resp);
                break;

            case "editar":
                Usuario u = dao.buscarPorId(parseId(req.getParameter("id")));
                if (u == null) {
                    resp.sendRedirect(req.getContextPath() + "/usuarios");
                    return;
                }
                req.setAttribute("usuario", u);
                req.getRequestDispatcher(VISTA_FORM).forward(req, resp);
                break;

            default:
                mostrarLista(req, resp, null);
        }
    }

    // ==========================
    // POST: guardar / eliminar
    // ==========================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String accion = req.getParameter("accion");

        if ("eliminar".equals(accion)) {
            eliminar(req, resp);
        } else {
            guardar(req, resp);
        }
    }

    private void guardar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = parseId(req.getParameter("id"));
        String nombre = limpiar(req.getParameter("nombre"));
        String correo = limpiar(req.getParameter("correo"));

        Usuario u = new Usuario(id, nombre, correo);

        // Validación básica
        if (nombre.isEmpty() || correo.isEmpty()) {
            volverAlFormulario(req, resp, u, "Nombre y correo son obligatorios.");
            return;
        }

        try {
            if (id == 0) {
                dao.insertar(u);
            } else {
                dao.actualizar(u);
            }
            resp.sendRedirect(req.getContextPath() + "/usuarios?ok=guardado"); // Post-Redirect-Get
        } catch (RuntimeException e) {
            volverAlFormulario(req, resp, u,
                    "No se pudo guardar. Revisa que el correo no esté repetido.");
        }
    }

    private void eliminar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            dao.eliminar(parseId(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/usuarios?ok=eliminado");
        } catch (RuntimeException e) {
            mostrarLista(req, resp, "No se pudo eliminar el usuario.");
        }
    }

    // ==========================
    // Auxiliares
    // ==========================
    private void mostrarLista(HttpServletRequest req, HttpServletResponse resp, String error)
            throws ServletException, IOException {
        req.setAttribute("usuarios", dao.listar());
        req.setAttribute("error", error);
        req.getRequestDispatcher(VISTA_LISTA).forward(req, resp);
    }

    private void volverAlFormulario(HttpServletRequest req, HttpServletResponse resp,
                                    Usuario u, String error)
            throws ServletException, IOException {
        req.setAttribute("usuario", u);
        req.setAttribute("error", error);
        req.getRequestDispatcher(VISTA_FORM).forward(req, resp);
    }

    private int parseId(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    private String limpiar(String s) {
        return s == null ? "" : s.trim();
    }
}
