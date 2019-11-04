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

@WebServlet(name="AltaPanServlet", urlPatterns= {"/pan"})
public class AltaPanServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("action", getServletContext().getContextPath() + "/pan");
		req.setAttribute("method", "post");
		req.getRequestDispatcher("/formpan.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String txtNombre = req.getParameter("txtNombre");
		String txtDesc = req.getParameter("txtDesc");
		String txtTamanho = req.getParameter("txtTamanho");
		String txtPrecio = req.getParameter("txtPrecio");
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		
		conn = DatabaseUtil.getConnection();
		
		if(conn != null) {
			// Crear objeto para el control de la base de dato
			dbManager = new DatabaseManager(conn);
			
			// Crear modelo y y poner datos
			Pan pan = new Pan();
			pan.setNombre(txtNombre);
			pan.setDescripcion(txtDesc);
			pan.setTamanho(txtTamanho);
			pan.setPrecio(Float.parseFloat(txtPrecio));
			
			// Realizar operacion insert en la base de datos
			dbManager.addPan(pan);
			DatabaseUtil.closeConnection(conn);
			resp.sendRedirect(getServletContext().getContextPath() + "/panes");
		} else {
			System.out.println("Hubo una falla en la conexion");
		}
		
	}

}
