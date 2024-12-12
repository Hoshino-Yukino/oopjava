package web.jpa.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import web.jpa.EntityManagerFactory;
import web.jpa.User;

/**
 * Servlet implementation class TodoUserCreate
 */
@WebServlet("/todousercreate")
public class TodoUserCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoUserCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/todo/createuser.jsp").forward(request, response);
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
			u.setPassword(new BCryptPasswordEncoder().encode(password));
			em.persist(u);
			request.setAttribute("id", u.getId());
			em.getTransaction().commit();
		}finally {
			em.close();
			request.getRequestDispatcher("/WEB-INF/todo/createuser.jsp").forward(request, response);
		}
	}

}
