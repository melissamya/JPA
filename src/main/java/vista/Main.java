/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import dao.UsuarioDao;
import dao.UsuarioDAOImpl;
import modelo.Usuario;
import util.JPAUtil;
import java.util.List;
/**
 *
 * @author Maya
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE CRUD CON JPA ===\n");

        UsuarioDao dao = new UsuarioDAOImpl();

        // 1. INSERTAR
        System.out.println("→ Insertando usuarios...");
        dao.insertar(new Usuario("Ana", "ana@mail.com"));
        dao.insertar(new Usuario("Luis", "luis@mail.com"));
        dao.insertar(new Usuario("Maria", "maria@mail.com"));

        // 2. LISTAR
        System.out.println("\n→ Lista de usuarios:");
        List<Usuario> lista = dao.listar();
        for (Usuario u : lista) {
            System.out.println("   " + u);
        }

        // 3. BUSCAR POR ID
        System.out.println("\n→ Buscando usuario con id=1:");
        Usuario encontrado = dao.buscarPorId(1);
        System.out.println("   " + (encontrado != null ? encontrado : "No encontrado"));

        // 4. ACTUALIZAR
        if (encontrado != null) {
            encontrado.setNombre("Ana Actualizada");
            encontrado.setCorreo("ana.nueva@mail.com");
            dao.actualizar(encontrado);
            System.out.println("\n→ Actualizando id=1: OK");
        }

        // 5. ELIMINAR
        dao.eliminar(3);
        System.out.println("\n→ Eliminando id=3: OK");

        // 6. LISTAR DE NUEVO
        System.out.println("\n→ Lista final:");
        for (Usuario u : dao.listar()) {
            System.out.println("   " + u);
        }

        // Cerrar la fábrica de EntityManagers
        JPAUtil.cerrar();
    }
}
