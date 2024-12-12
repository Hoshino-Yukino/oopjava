package web.jpa.cashbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.jpa.EntityManagerFactory;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/cashbook/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=utf8");
		var session = request.getSession(true);
		if(session.getAttribute("id")==null) {
			response.sendRedirect("login");
			return;
		}
		var id = session.getAttribute("id");
		var em = EntityManagerFactory.create();
		try {
			List<Data> dataList = em.createQuery("SELECT u FROM Data u WHERE u.user.id= :id ORDER BY u.recordTime DESC",Data.class)
					.setParameter("id",id)
					.getResultList();
			request.getSession().setAttribute("results", dataList);
			request.getRequestDispatcher("/WEB-INF/cashbook/home.jsp").forward(request, response);
		}finally {
			em.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf8");
		var session = request.getSession(true);
		var searchInfo = request.getParameter("search");
		
		if(session.getAttribute("id")==null) {
			response.sendRedirect("login");
			return;
		}
		var id = session.getAttribute("id");
		var em = EntityManagerFactory.create();
		try {
			List<Data> dataList = em.createQuery("SELECT u FROM Data u WHERE u.user.id= :id AND u.subject LIKE :searchInfo ORDER BY u.recordTime DESC",Data.class)
					.setParameter("id",id)
					.setParameter("searchInfo", "%" + searchInfo + "%")
					.getResultList();
			request.getSession().setAttribute("results", dataList);
			request.getRequestDispatcher("/WEB-INF/cashbook/home.jsp").forward(request, response);
		}finally {
			em.close();
		}
	}

}
