package webapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> users = new HashMap<>();
	
	@Override
	public void init() {
		users.put("user000", "user000");
		users.put("kcgedu","123456");
	}
       
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
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var username = request.getParameter("username");
		var password = request.getParameter("password");
		System.out.println(password);
		System.out.println(users.get(username));
		if(users.get(username)==null) {
			response.sendError(403);
		}else if (users.get(username).equals(password)) {
			request.getSession().setAttribute("username", username);
			request.getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request, response);

		}else {
			response.sendError(403);
		}

	}

}
