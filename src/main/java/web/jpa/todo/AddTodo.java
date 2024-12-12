package web.jpa.todo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.jpa.EntityManagerFactory;
import web.jpa.User;

/**
 * Servlet implementation class AddTodo
 */
@WebServlet("/addtodo")
public class AddTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendError(405);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=utf8");		
		var session = request.getSession(true);
		if(session.getAttribute("id")==null) {
			response.sendRedirect("todologin");
		}
		var user_id = session.getAttribute("id");
		String subject = request.getParameter("subject");
		String deadline = request.getParameter("deadline");
		int priority = Integer.parseInt(request.getParameter("priority"));
		var em = EntityManagerFactory.create();
		try {
			em.getTransaction().begin();
			List<User> userList = em.createQuery("SELECT u FROM User u WHERE u.id= :id",User.class)
					.setParameter("id",user_id)
					.getResultList();
			var ts = new  java.sql.Timestamp(new SimpleDateFormat("yyyy/MM/dd HH:mm")
					.parse(deadline).getTime());
			var todo = new Todo(subject,ts,priority,userList.get(0));
			em.persist(todo);
			userList.get(0).getDatas().add(todo);
			em.getTransaction().commit();
			response.sendRedirect("todos");
			
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		

	}

}
