package panaderiaapp1;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseManager;

@WebServlet(name="BajaPanServlet", urlPatterns= {"/deletePan"})
public class BajaPanServlet extends  HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("deletepan.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = 0;
		
		id = Integer.parseInt(req.getParameter("txtId"));
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		
		conn = DatabaseUtil.getConnection();
		
		if (conn != null) {
			dbManager = new DatabaseManager(conn);
			dbManager.deletePan(id);
			DatabaseUtil.closeConnection(conn);
			resp.sendRedirect(getServletContext().getContextPath() + "/panes");
		} else {
			System.out.println("Hubo una falla en la conexion");
		}
		
	}

}
