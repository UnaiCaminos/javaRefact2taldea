package interfaz_grafica;

import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField inserttext;
	private JTextField ezabatutext;
	private JTextField aldatu_text;
	String opciontabla;
	Statement st;
	ResultSet rs;
	
	Inicio ini = new Inicio();
	String ip = ini.ip;
	String BBDD = ini.BBDD;
	String usuario=ini.usuario;
	String contrasena=ini.contraseña;
	private JTextField select_columna;
	private JTextField between_text;
	private JButton btnBetween;
	private JTextField mayorque;
	private JButton mayor;
	
	static BufferedImage fitImage(JLabel label, BufferedImage image) {
        int newWidth = label.getWidth();
        int newHeight = label.getHeight();
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();
        label.setIcon(new ImageIcon(resizedImage));
        return resizedImage;
    }
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public void actualizartabla(String opciontabla) {
		try
		{
		   Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception a)
		{
		   a.printStackTrace();
		}
		// Establecemos la conexiÃ³n con la base de datos.
		try {
			Connection conexion = DriverManager.getConnection (ip,usuario,contrasena);
			//Preparamos la consulta
			DefaultTableModel model = new DefaultTableModel();
			st = conexion.createStatement();
			rs = st.executeQuery ("select * from "+BBDD+"."+opciontabla);
			ResultSetMetaData metaData = rs.getMetaData();
			table.setModel(model);
			TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(model);
			table.setRowSorter(elQueOrdena);
			int columnCount = metaData.getColumnCount();
	           
			for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }
			
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                model.addRow(row);
            }	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	public void actualizar(ResultSet rs) {
		try {
			DefaultTableModel model = new DefaultTableModel();
			ResultSetMetaData metaData = rs.getMetaData();
			table.setModel(model);
			int columnCount = metaData.getColumnCount();
	           
			for (int i = 1; i <= columnCount; i++) {
	            model.addColumn(metaData.getColumnName(i));
	        }
			
	        while (rs.next()) {
	            Object[] row = new Object[columnCount];
	            for (int i = 1; i <= columnCount; i++) {
	                row[i - 1] = rs.getString(i);
	            }
	            model.addRow(row);
	        }	
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void eliminarEmpleado(String opciontabla){
			String sartu = ezabatutext.getText();
			String[] partes = sartu.split(";");
			 Connection conexion;
			 try {
				conexion = DriverManager.getConnection (ip,usuario,contrasena);
				Statement st = conexion.createStatement();
				ResultSet rs = st.executeQuery("select "+partes[0]+" from "+opciontabla);
				ResultSetMetaData rsmd = rs.getMetaData();
				String tipo = rsmd.getColumnTypeName(1);
				
				if(tipo == "INT") {
					PreparedStatement stmt= conexion.prepareStatement("delete from "+opciontabla+" where "+partes[0]+" = ?");
					stmt.setInt(1, Integer.parseInt(partes[1]));
					stmt.executeUpdate();
				}else {
					PreparedStatement stmt= conexion.prepareStatement("delete from "+opciontabla+" where "+partes[0]+" = ?");
					stmt.setString(1, partes[1]);
					 stmt.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 actualizartabla(opciontabla);
		}
	
	public void aldatudatuak(String opciontabla) {
		//UPDATE table name SET field name  = value WHERE [Last Name] = 'Smith' 
		String sartu = aldatu_text.getText();
		String[] partes = sartu.split(";");
		 Connection conexion;
		 try {
			conexion = DriverManager.getConnection (ip,usuario,contrasena);
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select "+partes[0]+" from "+opciontabla);
			ResultSetMetaData rsmd = rs.getMetaData();
			String tipo = rsmd.getColumnTypeName(1);
			
			if(tipo == "INT") {
				PreparedStatement stmt= conexion.prepareStatement("UPDATE "+opciontabla+ " SET "+partes[2]+" = ? WHERE "+partes[0]+" = ?");
				stmt.setInt(1, Integer.parseInt(partes[3]));
				stmt.setInt(2, Integer.parseInt(partes[1]));
				stmt.executeUpdate();
			}else {
				PreparedStatement stmt= conexion.prepareStatement("UPDATE "+opciontabla+ " SET "+partes[2]+" = ? WHERE "+partes[0]+" = ?");
				stmt.setString(1, partes[3]);
				stmt.setString(2, partes[1]);
				 stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 actualizartabla(opciontabla);
	}
	public void select(String opciontabla) {
		String sartu = select_columna.getText();
		String[] partes = sartu.split(";");
		 Connection conexion;
		 ResultSet rs;
		 try {
			conexion = DriverManager.getConnection (ip,usuario,contrasena);
			Statement st = conexion.createStatement();
			if(isNumeric(partes[1])) {
				rs = st.executeQuery("select * from " + opciontabla + " WHERE " + partes[0] + " =" + Integer.parseInt(partes[1])+";");
			}else {
				rs = st.executeQuery("select * from " + opciontabla + " WHERE " + partes[0] + " ='" + partes[1]+"';");
			}
			actualizar(rs);
			actualizartabla(opciontabla);
		 } catch (SQLException e) {
				e.printStackTrace();
		 }
	}
	public void between(String opciontabla) {
		String sartu = between_text.getText();
		String[] partes = sartu.split(";");
		 Connection conexion;
		 ResultSet rs;
		 try {
			conexion = DriverManager.getConnection (ip,usuario,contrasena);
			Statement st = conexion.createStatement();
			if(isNumeric(partes[1])&&isNumeric(partes[2])) {
				rs = st.executeQuery("select * from " + opciontabla + " WHERE " + partes[0] + " between " + Integer.parseInt(partes[1])+" AND "+Integer.parseInt(partes[2])+";");
			}else {
				rs = st.executeQuery("select * from " + opciontabla + " WHERE " + partes[0] + " between " + partes[1]+" AND "+partes[2]+";");
			}
			actualizar(rs);
		 } catch (SQLException e) {
				e.printStackTrace();
		 }
	}
	public void mayor(String opciontabla) {
		String sartu = mayorque.getText();
		String[] partes = sartu.split(";");
		 Connection conexion;
		 ResultSet rs;
		 try {
			conexion = DriverManager.getConnection (ip,usuario,contrasena);
			Statement st = conexion.createStatement();
			if(isNumeric(partes[2])) {
				rs = st.executeQuery("select * from " + opciontabla + " WHERE " + partes[0] +" "+ partes[1] +" "+ Integer.parseInt(partes[2])+";");
			}else {
				rs = st.executeQuery("select * from " + opciontabla + " WHERE " + partes[0] +" "+ partes[1] +" "+ partes[2]+";");
			}
			actualizar(rs);
		 } catch (SQLException e) {
				e.printStackTrace();
		 }
	}
	public Interfaz() {
		setForeground(new Color(64, 0, 64));
		setResizable(false);
		setTitle("Administrador de tablas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cargar base");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(10, 43, 104, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(124, 11, 739, 410);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"langileak", "konponenteak", "hornitzaileak", "bezeroak", "berriak"}));
		comboBox.setBounds(10, 10, 104, 22);
		contentPane.add(comboBox);
		
		inserttext = new JTextField("dato1;dato2;...");
		inserttext.setBounds(246, 432, 365, 22);
		contentPane.add(inserttext);
		inserttext.setColumns(10);
		//tabla birkargatu
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciontabla = comboBox.getSelectedItem().toString();
				actualizartabla(opciontabla);
				
			}
		});
		
		
		//Lineak gehitu
		JButton btnNewButton_1 = new JButton("Sartu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conexion;
				String sartu = inserttext.getText();
				String[] partes = sartu.split(";");
				switch(opciontabla) {
					case "langileak":
						try {
							conexion = DriverManager.getConnection (ip,usuario,contrasena);
							st = conexion.createStatement();
							PreparedStatement stmt = conexion.prepareStatement("INSERT INTO langileak (izena,abizena1,abizena2,nan,banku_zenbakia,langilearen_funtzioa,korreoa,helbidea,sexua,admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							stmt.setString(1, partes[0]);
							stmt.setString(2, partes[1]);
							stmt.setString(3, partes[2]);
							stmt.setInt(4, Integer.parseInt(partes[3]));
							stmt.setString(5, partes[4]);
							stmt.setString(6, partes[5]);
							stmt.setString(7, partes[6]);
							stmt.setString(8, partes[7]);
							stmt.setString(9, partes[8]);
							stmt.setString(10, partes[9]);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					break;
					case "komponenteak":
						try {
							conexion = DriverManager.getConnection (ip,usuario,contrasena);
							st = conexion.createStatement();
							PreparedStatement stmt = conexion.prepareStatement("INSERT INTO komponenteak (id,konponenteMota,img,modelo,descripzioa,kantitatea,berria,prezioa,marka,balorazioa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							stmt.setInt(1, Integer.parseInt(partes[0]));
							stmt.setString(2, partes[1]);
							stmt.setString(3, partes[2]);
							stmt.setString(4, partes[3]);
							stmt.setString(5, partes[4]);
							stmt.setInt(6, Integer.parseInt(partes[5]));
							stmt.setInt(7, Integer.parseInt(partes[6]));
							stmt.setFloat(8, Float.parseFloat(partes[7]));
							stmt.setString(9, partes[8]);
							stmt.setInt(10, Integer.parseInt(partes[9]));
							stmt.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					break;
					case "hornitzaileak":
						try {
							conexion = DriverManager.getConnection (ip,usuario,contrasena);
							st = conexion.createStatement();
							PreparedStatement stmt = conexion.prepareStatement("INSERT INTO hornitzaileak (id,EmpresarekoTlfZenbakia,EmpresarenIzena,EmpresarenKorreoa,Helbidea,NAN) VALUES (?, ?, ?, ?, ?, ?)");
							stmt.setInt(1, Integer.parseInt(partes[0]));
							stmt.setInt(2, Integer.parseInt(partes[1]));
							stmt.setString(3, partes[2]);
							stmt.setString(4, partes[3]);
							stmt.setString(5, partes[4]);
							stmt.setString(6, partes[5]);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					break;
					case "bezeroak":
						try {
							conexion = DriverManager.getConnection (ip,usuario,contrasena);
							st = conexion.createStatement();
							PreparedStatement stmt = conexion.prepareStatement("INSERT INTO bezeroak (izena,abizena1,abizena2,nan,banku_zenbakia,helbidea,sexua) VALUES (?, ?, ?, ?, ?, ?, ?)");
							stmt.setString(1, partes[0]);
							stmt.setString(2, partes[1]);
							stmt.setString(3, partes[2]);
							stmt.setString(4, partes[3]);
							stmt.setString(5, partes[4]);
							stmt.setString(6, partes[5]);
							stmt.setString(7, partes[6]);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					break;
					case "berriak":
						try {
							conexion = DriverManager.getConnection (ip,usuario,contrasena);
							st = conexion.createStatement();
							PreparedStatement stmt = conexion.prepareStatement("INSERT INTO berriak (id, izenburua, descripcioLaburra, fecha) VALUES (?, ?, ?, ?)");
							stmt.setInt(1, Integer.parseInt(partes[0]));
							stmt.setString(2, partes[1]);
							stmt.setString(3, partes[2]);
							stmt.setString(4, partes[3]);
							stmt.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					break;
				}	
				opciontabla = comboBox.getSelectedItem().toString();
				actualizartabla(opciontabla);
			}
		});
		btnNewButton_1.setBounds(149, 432, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		JButton borratu = new JButton("Ezabatu");
		
		borratu.setBounds(149, 466, 89, 23);
		contentPane.add(borratu);
		
		ezabatutext = new JTextField();
		ezabatutext.setText("columna;valor");
		ezabatutext.setBounds(246, 465, 365, 22);
		contentPane.add(ezabatutext);
		ezabatutext.setColumns(10);
		
		aldatu_text = new JTextField();
		aldatu_text.setText("columna identificador;valor; columna cambio; nuevo valor");
		aldatu_text.setColumns(10);
		aldatu_text.setBounds(246, 498, 365, 22);
		contentPane.add(aldatu_text);
		
		JButton Aldatu = new JButton("Aldatu");
		Aldatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aldatudatuak(opciontabla);
			}
		});
		Aldatu.setBounds(149, 498, 89, 23);
		contentPane.add(Aldatu);
		
		select_columna = new JTextField(" Columna;valor\r\n");
		select_columna.setColumns(10);
		select_columna.setBounds(10, 77, 104, 22);
		contentPane.add(select_columna);
		
		JButton selectbutton = new JButton("Select");
		selectbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select(opciontabla);
			}
		});
		selectbutton.setBounds(10, 110, 104, 23);
		contentPane.add(selectbutton);
		
		between_text = new JTextField("columna;valor1;valor2");
		between_text.setColumns(10);
		between_text.setBounds(10, 146, 104, 22);
		contentPane.add(between_text);
		
		btnBetween = new JButton("between");
		btnBetween.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				between(opciontabla);
			}
		});
		btnBetween.setBounds(10, 179, 104, 23);
		contentPane.add(btnBetween);
		
		mayorque = new JTextField(" Columna;signo;valor\r\n");
		mayorque.setColumns(10);
		mayorque.setBounds(10, 214, 104, 22);
		contentPane.add(mayorque);
		
		mayor = new JButton("Filtro >");
		mayor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mayor(opciontabla);
			}
		});
		mayor.setBounds(10, 248, 104, 23);
		contentPane.add(mayor);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(646, 432, 217, 118);

		BufferedImage imagen = null;
		try {
			imagen = ImageIO.read(new File("imagenes/logo.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		fitImage(lblNewLabel,imagen);
		contentPane.add(lblNewLabel);
		borratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciontabla = comboBox.getSelectedItem().toString();
				eliminarEmpleado(opciontabla);
			}
		});
		opciontabla = comboBox.getSelectedItem().toString();
		actualizartabla(opciontabla);
	}
}

