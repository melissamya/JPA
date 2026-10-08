package controlador;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import util.JPAUtil;

/**
 * Cierra el EntityManagerFactory cuando Tomcat detiene o recarga la aplicación,
 * para no dejar conexiones abiertas a MySQL.
 */
@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAUtil.cerrar();
    }
}
