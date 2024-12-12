package web.jpa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindUserServlet
 */
@WebServlet("/find")
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var id = request.getParameter("id");
		System.out.println(id);
		response.setContentType("text/plain;charset=utf8");
		var em = EntityManagerFactory.create();
		try {
			var u1 = em.find(User.class,Integer.valueOf(id));
			if (u1!=null) {
			request.setAttribute("id", u1.getId());
			request.setAttribute("name",u1.getName());
			request.setAttribute("userId", u1.getUserId());
			}
		}finally {
			em.close();
			request.getRequestDispatcher("/WEB-INF/jpa/find.jsp").forward(request, response);
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
