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
 * Servlet implementation class DeleteData
 */
@WebServlet("/cashbook/delete")
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=utf8");
        var session = request.getSession(true);
        var user_id = session.getAttribute("id");
        var dataId = request.getParameter("id");
        if (session.getAttribute("id") == null) {
            response.sendRedirect("login");
            return;
        }
        var em = EntityManagerFactory.create();
        try {
            em.getTransaction().begin();
            List<Data> dataList = em.createQuery("SELECT u FROM Data u WHERE u.id= :id AND u.user.id=:user_id", Data.class)
                    .setParameter("user_id", user_id)
                    .setParameter("id", Integer.valueOf(dataId))
                    .getResultList();
            em.remove(dataList.get(0));
            em.getTransaction().commit();
            response.sendRedirect("home");
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
