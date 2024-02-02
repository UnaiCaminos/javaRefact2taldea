
package erronka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class konexioaLocal {
	private static String usuario="root";
	private static String contraseña="1WMG2023";
    private static String url = "jdbc:mysql://localhost:3306/erronka";
    private static String BBDD = "com.mysql.cj.jdbc.Driver";
	
	static {
		try {
			Class.forName(konexioaLocal.BBDD);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Driver no Conectado");
		}
	}
	
		Connection con=null;
		
		public Connection getConnection() {
			try {
				con=DriverManager.getConnection(url, usuario, contraseña);
			}catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error de Conexion");
			}
			return con;
		}
}
