package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import modelo.Usuario;
import util.JPAUtil;
import java.util.List;

/**
 * Implementación JPA del DAO de Usuario.
 * Las operaciones de escritura relanzan la excepción para que el
 * Servlet pueda mostrar el error al usuario (por ejemplo, correo duplicado).
 *
 * @author Maya
 */
public class UsuarioDAOImpl implements UsuarioDao {

    // ==========================
    // INSERTAR
    // ==========================
    @Override
    public void insertar(Usuario u) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(u);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error al insertar: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    // ==========================
    // LISTAR TODOS
    // ==========================
    @Override
    public List<Usuario> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u ORDER BY u.id", Usuario.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    @Override
    public Usuario buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    @Override
    public void actualizar(Usuario u) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(u);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error al actualizar: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    // ==========================
    // ELIMINAR
    // ==========================
    @Override
    public void eliminar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario u = em.find(Usuario.class, id);
            if (u != null) {
                em.remove(u);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Error al eliminar: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}