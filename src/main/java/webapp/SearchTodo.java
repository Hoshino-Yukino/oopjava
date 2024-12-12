package webapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.jpa.EntityManagerFactory;
import web.jpa.todo.Todo;

/**
 * Servlet implementation class SearchTodo
 */
@WebServlet("/searchtodo")
public class SearchTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTodo() {
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
		var session = request.getSession(true);
		if(session.getAttribute("id")==null) {
			response.sendRedirect("todologin");
		}
		var id = session.getAttribute("id");
		String subject = request.getParameter("subject");
		var em = EntityManagerFactory.create();
		try {
			List<Todo> todoList = em.createQuery("SELECT u FROM Todo u WHERE u.user.id= :id AND u.subject LIKE :subject",Todo.class)
					.setParameter("id",id)
					.setParameter("subject","%" + subject + "%")
					.getResultList();

			request.setAttribute("results", todoList);
			request.getRequestDispatcher("/WEB-INF/todo/todos.jsp").forward(request, response);
		}finally {
			em.close();
		}
	}

}
