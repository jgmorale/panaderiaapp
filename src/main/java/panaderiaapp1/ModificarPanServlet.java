package panaderiaapp1;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Pan;
import util.DatabaseManager;

@WebServlet(name="ModificarPanServlet", urlPatterns= {"/modPan"})
public class ModificarPanServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("modifypan.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String txtId = req.getParameter("txtId");
		String txtNombre = req.getParameter("txtNombre");
		String txtDesc = req.getParameter("txtDesc");
		String txtTamanho = req.getParameter("txtTamanho");
		String txtPrecio = req.getParameter("txtPrecio");
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		
		conn = DatabaseUtil.getConnection();
		
		if (conn != null) {
			dbManager = new DatabaseManager(conn);
			
			Pan pan = new Pan();
			pan.setId(Integer.parseInt(txtId));
			pan.setNombre(txtNombre);
			pan.setDescripcion(txtDesc);
			pan.setTamanho(txtTamanho);
			pan.setPrecio(Float.parseFloat(txtPrecio));

			dbManager.updatePan(pan);
			DatabaseUtil.closeConnection(conn);
			resp.sendRedirect(getServletContext().getContextPath() + "/panes");
		} else {
			System.out.println("Hubo una falla con la conexion");
		}
	}

}
