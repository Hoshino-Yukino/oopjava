package web.jpa.cashbook;

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
 * Servlet implementation class Register
 */
@WebServlet("/cashbook/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var session = request.getSession(true);
		if(session.getAttribute("username")==null) {
			request.getRequestDispatcher("/WEB-INF/cashbook/register.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/cashbook/home.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var userId = request.getParameter("userId");
		var password = request.getParameter("password");
		var username = request.getParameter("name");
		var em = EntityManagerFactory.create();
		em.getTransaction().begin();
		try {
			List<User> userList = em.createQuery("SELECT u FROM User u WHERE u.userId= :id",User.class)
			.setParameter("id",userId).getResultList();
			if (userList.size()>0) {
				request.setAttribute("message", "ユーザーIDは既に登録されています。修正して再試行してください。");
				request.getRequestDispatcher("/WEB-INF/cashbook/register.jsp").forward(request, response);
			}else {
				var u = new User();
				u.setName(username);
				u.setUserId(userId);
				u.setPassword(new BCryptPasswordEncoder().encode(password));
				em.persist(u);
				em.getTransaction().commit();
				response.sendRedirect("home");
			}

		}finally {
			em.close();
		}
	}

}
