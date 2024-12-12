package web.jpa.todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.jpa.EntityManagerFactory;

/**
 * Servlet implementation class AllTodos
 */
@WebServlet("/alltodos")
public class AllTodos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllTodos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var em = EntityManagerFactory.create();
		try {
			List<Todo> todoList = em.createQuery("SELECT u FROM Todo u",Todo.class)
					.getResultList();

			request.setAttribute("results", todoList);
			request.getRequestDispatcher("/WEB-INF/todo/todos.jsp").forward(request, response);
		}finally {
			em.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
