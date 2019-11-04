package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import models.Pan;

public class DatabaseManager {
	private Connection conn;
	
	public DatabaseManager(Connection conn) {
		this.conn = conn;
	}
	
	public int addPan(Pan pan) {
		String query = "INSERT INTO pan(nombre, precio, tamanho, descripcion) VALUES (?, ?, ?, ?)";
		PreparedStatement stmnt = null;
		int result = -1;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1,pan.getNombre());
			stmnt.setFloat(2,pan.getPrecio());
			stmnt.setString(3,pan.getTamanho());
			stmnt.setString(4,pan.getDescripcion());
			
			result = stmnt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		return result;
		
	}
	
	public int updatePan(Pan pan) {
		String query = "UPDATE pan SET nombre=?, precio=?, tamanho=?, descripcion=? WHERE id=?";
		PreparedStatement stmnt = null;
		int result = -1;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setString(1, pan.getNombre());
			stmnt.setFloat(2,pan.getPrecio());
			stmnt.setString(3,pan.getTamanho());
			stmnt.setString(4,pan.getDescripcion());
			stmnt.setInt(5, pan.getId());
			
			result = stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public int deletePan(int id) {
		String query = "DELETE FROM pan WHERE id=?";
		PreparedStatement stmnt = null;
		int result = -1;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, id);
			result = stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public List<Pan> getPanes(){
		List<Pan> listaPanes = new ArrayList<>();
		Statement stmnt = null;
		CallableStatement statement = null; //Investigar que es lo que hace
		ResultSet rs = null;
		String query = "SELECT * FROM pan";
		try {
			stmnt = conn.createStatement();
			rs = stmnt.executeQuery(query);
			while (rs.next()) {
				Pan pan = new Pan();
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				float precio = rs.getFloat(3);
				String tamanho = rs.getString(4);
				Date fecha = rs.getDate(5);
				String descripcion = rs.getString(6);

				pan.setId(id);
				pan.setNombre(nombre);
				pan.setPrecio(precio);
				pan.setTamanho(tamanho);
				pan.setFecha(fecha);
				pan.setDescripcion(descripcion);

				listaPanes.add(pan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		}
		return listaPanes;
	}
	
	public Pan getPanById(int id) {
		String query = "SELECT * FROM pan WHERE id=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		Pan pan = null;
		//System.out.println(id);
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, id);
			//System.out.println("Si entro al try");
			//System.out.println(query);
			rs = stmnt.executeQuery();
			if (rs.first()) {
				
				String nombre = rs.getString(2);
				String descripcion = rs.getString(6);
				String tamanho = rs.getString(4);
				float precio = rs.getFloat(3);
				Date fecha = rs.getDate(5);
				
				pan = new Pan();
				pan.setId(id);
				pan.setNombre(nombre);
				pan.setDescripcion(descripcion);
				pan.setFecha(fecha);
				pan.setPrecio(precio);
				pan.setTamanho(tamanho);
			}
		} catch (SQLException e) {
				//System.out.println("Entro al catch");
				e.printStackTrace();
		} finally {
			if(stmnt!=null) {
				try {
					stmnt.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException r) {
					r.printStackTrace();
				}
			}
		} 
		System.out.println(pan.getId());
		System.out.println(pan.getNombre());
		System.out.println(pan.getDescripcion());
		System.out.println(pan.getTamanho());
		System.out.println(pan.getPrecio());
		return pan;
	}
	
	
}
