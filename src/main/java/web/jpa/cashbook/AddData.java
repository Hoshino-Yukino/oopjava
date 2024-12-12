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
import web.jpa.User;

/**
 * Servlet implementation class AddData
 */
@WebServlet("/cashbook/adddata")
public class AddData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddData() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.sendError(405);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=utf8");
        var session = request.getSession(true);
        if (session.getAttribute("id") == null) {
            response.sendRedirect("login");
            return;
        }
        var user_id = session.getAttribute("id");
        String subject = request.getParameter("subject");
        String recordTime = request.getParameter("time");
        int category = Integer.valueOf(request.getParameter("category"));
        int price = Integer.parseInt(request.getParameter("price"));
        var em = EntityManagerFactory.create();
        try {
            em.getTransaction().begin();
            List<User> userList = em.createQuery("SELECT u FROM User u WHERE u.id= :id", User.class)
                    .setParameter("id", user_id)
                    .getResultList();
            var ts = new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(recordTime).getTime());
            var data = new Data(subject, ts, category, price, userList.get(0));
            em.persist(data);
            userList.get(0).getDatas().add(data);
            em.getTransaction().commit();
            response.sendRedirect("home");

        } catch (ParseException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
