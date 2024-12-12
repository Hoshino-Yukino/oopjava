package web.jpa.cashbook;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import web.jpa.EntityManagerFactory;
import web.jpa.User;

@WebListener
public class Initializer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		EntityManagerFactory.initialize("jpa");
		var em = EntityManagerFactory.create();
		try {
			var c = (Number)em.createQuery("select count(u)from User u").getSingleResult();
			if(c.intValue()==0) {
				addTestUser(em);
			}
		}finally {
			em.close();
		}
	}
	private void addTestUser(EntityManager em) {
        try {
            em.getTransaction().begin();
            var u = new User("testid",
                    new BCryptPasswordEncoder().encode("123456"),
                    "testname");
            em.persist(u);
            var data = new Data("初期記録", new java.sql.Timestamp(new Date().getTime()), 0, 0, u);
            em.persist(data);
            u.getDatas().add(data);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding test user", e);
        }
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		EntityManagerFactory.destory();
	}
}
