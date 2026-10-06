package dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import modelo.Usuario;
import util.JPAUtil;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
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
            System.err.println("Error al insertar: " + e.getMessage());
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
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class)
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
            System.err.println("Error al actualizar: " + e.getMessage());
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
            System.err.println("Error al eliminar: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}