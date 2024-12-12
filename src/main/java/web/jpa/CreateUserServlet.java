package web.jpa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/jpausercreate")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jpa/createuser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		var name = request.getParameter("name");
		var userId = request.getParameter("userId");
		var password = request.getParameter("password");
		var em = EntityManagerFactory.create();
		em.getTransaction().begin();
		try {
			var u = new User();
			u.setName(name);
			u.setUserId(userId);
			u.setPassword(password);
			em.persist(u);
			request.setAttribute("id", u.getId());
			em.getTransaction().commit();
		}finally {
			em.close();
			request.getRequestDispatcher("/WEB-INF/jpa/createuser.jsp").forward(request, response);
		}
	}

}
