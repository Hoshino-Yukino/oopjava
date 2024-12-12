//package web.jpa;
//
//import java.util.Date;
//
//import javax.persistence.EntityManager;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import web.jpa.todo.Todo;
//
//@WebListener
//public class Initializer implements ServletContextListener {
//	@Override
//	public void contextInitialized(ServletContextEvent event) {
//		EntityManagerFactory.initialize("jpa");
//		var em = EntityManagerFactory.create();
//		try {
//			var c = (Number)em.createQuery("select count(u)from User u").getSingleResult();
//			if(c.intValue()==0) {
//				addTestUser(em);
//			}
//		}finally {
//			em.close();
//		}
//	}
//	private void addTestUser(EntityManager em) {
//		em.getTransaction().begin();
//		var u = new User("user000id",
//				new BCryptPasswordEncoder().encode("user000pass"),
//				"user000");
//		em.persist(u);
//		var todo = new Todo("夕食を食べる",new java.sql.Timestamp(new Date().getTime()),1,u);
//		em.persist(todo);
//		u.getTodos().add(todo);
//		em.getTransaction().commit();
//	}
//	@Override
//	public void contextDestroyed(ServletContextEvent event) {
//		EntityManagerFactory.destory();
//	}
//}
