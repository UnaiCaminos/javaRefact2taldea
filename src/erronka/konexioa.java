package erronka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class konexioa {
	private static String BBDD="com.mysql.cj.jdbc.Driver";
	private static String usuario="root";
	private static String contraseña="1WMG2023";
	public static String url="jdbc:mysql://localhost:3306/erronka";
	
	static {
		try {
			Class.forName(url);
			JOptionPane.showMessageDialog(null, "Driver Conectado");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Driver no Conectado");
		}
	}
	
		Connection con=null;
		public Connection getConnection() {
			try {
				con=DriverManager.getConnection(url, usuario, contraseña);
				JOptionPane.showMessageDialog(null, "Conexion a Mysql Correcta");
			}catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error de Conexion");
			}
			return con;
		}
}

