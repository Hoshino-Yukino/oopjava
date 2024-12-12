package web.jpa;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "JpaLoginServlet", urlPatterns = { "/jpalogin" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(true);
		if(session.getAttribute("username")==null) {
			request.getRequestDispatcher("/WEB-INF/jpa/login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/jpa/welcome.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var userId = request.getParameter("userId");
		var password = request.getParameter("password");
		System.out.println(userId);
		var em = EntityManagerFactory.create();
		try {
			List<User> userList = em.createQuery("SELECT u FROM User u WHERE u.userId= :id AND u.password = :pwd",User.class)
			.setParameter("id",userId).setParameter("pwd", password)
			.getResultList();
			if (userList.size()>0) {
				request.getSession().setAttribute("userName", userList.get(0).getName());
				request.getRequestDispatcher("/WEB-INF/jpa/welcome.jsp").forward(request, response);
			}else {
				response.sendError(403);
			}
		}finally {
			em.close();
		}

	}

}
