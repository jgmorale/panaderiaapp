package panaderiaapp1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Pan;

@WebServlet(name="ListPanServlet", urlPatterns= {"/panes"})
public class ListPanServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("Si llego al get");
		// Identificar fuente de datos (Base de datos, archivo, REST API)
		List<Pan> listaPanes = new ArrayList<Pan>();
		
		// Objeto para establecer la conexion
		Connection conn = null;
		
		// Objeto para decirle a la base de datos que haga (operacion)
		Statement stmnt = null;
		
		// Objeto para manipular los datos que regresa la consulta (operacion)
		ResultSet rs = null;
		
		// Consulta para obtencion de datos
		String query = "SELECT * FROM pan";
		
		//Obtener conexion a la base de datos
		conn = DatabaseUtil.getConnection();
		
		if(conn != null) {
			try {
				
				// Crear comando para realizar operaciones en la base de datos
				// SELECT, INSERT, UPDATE, DELETE, CREATE
				stmnt = conn.createStatement();
				
				// Ejecuta la operacion indicada por el comando
				// Guardar los resultados obtenidos
				rs = stmnt.executeQuery(query);
				
				// Iterar todo el conjunto de resultados por fila
				while(rs.next()) {
					
					// Crear instancia del modelo
					// para encapsular informacion
					Pan pan = new Pan();
					
					// Obtener valores de las celdas
					// por su tipo de dato a traves de su indice
					int id = rs.getInt(1);
					String nombre = rs.getString(2);
					float precio = rs.getFloat(3);
					String tamanho = rs.getString(4);
					Date fecha = rs.getDate(5);
					String descripcion = rs.getString(6);
					
					// Llenar de datos el POJO
					pan.setId(id);
					pan.setNombre(nombre);
					pan.setPrecio(precio);
					pan.setTamanho(tamanho);
					pan.setFecha(fecha);
					pan.setDescripcion(descripcion);
					
					//  Agregar modelo a la lista
					listaPanes.add(pan);
				}
				
				stmnt.close();
				rs.close();
				DatabaseUtil.closeConnection(conn);
				req.setAttribute("listaPanes", listaPanes);
				//System.out.println("Si llego a antes del jsp");
				//System.out.println(listaPanes.get(0).getId());
				RequestDispatcher dispatcher = req.getRequestDispatcher("panes.jsp");
				dispatcher.forward(req, resp);
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} else {
			resp.setContentType("text/html");
			resp.getWriter().println("<h2>No se conecto a la base de datos<h2>");
		}
		
	}
		
}
