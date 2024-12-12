package web.jpa;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchUsersServlet
 */
@WebServlet("/search")
public class SearchUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchUsersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		response.setContentType("text/plain;charset=utf8");
		var em = EntityManagerFactory.create();
		try {
			List<User> list = em.createQuery(
					"SELECT u FROM User u WHERE u.name LIKE :name", User.class)
					.setParameter("name", "%" + name + "%")
					.getResultList();
			request.setAttribute("users", list);
			request.getRequestDispatcher("/WEB-INF/jpa/search.jsp").forward(request, response);
		} finally {
			em.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
