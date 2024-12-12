package web.jpa.cashbook;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.jpa.EntityManagerFactory;

/**
 * Servlet implementation class Modify
 */
@WebServlet("/cashbook/modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
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
		var dataid = request.getParameter("id");
		if(session.getAttribute("id")==null) {
			response.sendRedirect("todologin");
		}
		var id = session.getAttribute("id");
		var em = EntityManagerFactory.create();
		try {
			List<Data> dataList = em.createQuery("SELECT u FROM Data u WHERE u.user.id= :id AND id=:dataid",Data.class)
					.setParameter("id",id)
					.setParameter("dataid", Integer.valueOf(dataid))
					.getResultList();
			request.getSession().setAttribute("results", dataList);
			request.getRequestDispatcher("/WEB-INF/cashbook/modify.jsp").forward(request, response);
		}finally {
			em.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=utf8");
        var session = request.getSession(true);
        var user_id = session.getAttribute("id");
        var dataId = request.getParameter("id");
        String subject = request.getParameter("subject");
        String recordTime = request.getParameter("time");
        int category = Integer.valueOf(request.getParameter("category"));
        int price = Integer.parseInt(request.getParameter("price"));
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
            dataList.get(0).setSubject(subject);
            dataList.get(0).setRecordTime( new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(recordTime).getTime()));
            dataList.get(0).setCategory(category);
            dataList.get(0).setPrice(price);
            em.persist(dataList.get(0));
            em.getTransaction().commit();
            response.sendRedirect("home");
        } catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			em.close();
		}
	}

}
