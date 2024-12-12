package web.jpa.todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import web.jpa.EntityManagerFactory;
import web.jpa.User;

/**
 * Servlet implementation class TodoLogin
 */
@WebServlet("/todologin")
public class TodoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(true);
		if(session.getAttribute("username")==null) {
			request.getRequestDispatcher("/WEB-INF/todo/login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/todo/todos.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var userId = request.getParameter("userId");
		var password = request.getParameter("password");
		var em = EntityManagerFactory.create();
		try {
			List<User> userList = em.createQuery("SELECT u FROM User u WHERE u.userId= :id",User.class)
			.setParameter("id",userId).getResultList();
			if (userList.size()>0) {
				if(new BCryptPasswordEncoder().matches(password, userList.get(0).getPassword())) {
					request.getSession().setAttribute("id", userList.get(0).getId());
					request.getSession().setAttribute("name", userList.get(0).getName());
					response.sendRedirect("todos");
				}else {
					response.sendError(403);
				}
			}else {
				response.sendError(403);
			}
		}finally {
			em.close();
		}
	}
}
