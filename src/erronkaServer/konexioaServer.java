package erronkaServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class konexioaServer {
	private static String BBDD="com.mysql.cj.jdbc.Driver";
	private static String usuario="2taldea";
	private static String contraseña="2taldea@";
	public static String url="jdbc:mysql://192.168.115.2:3306/erronka2";
	
	static {
		try {
			Class.forName(BBDD);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Driver no Conectado");
		}
	}
	
		konexioaServerData data = new konexioaServerData(null);
		public Connection getConnection() {
			try {
				data.con2=DriverManager.getConnection(url, usuario, contraseña);
			}catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error de Conexion");
			}
			return data.con2;
		}
}