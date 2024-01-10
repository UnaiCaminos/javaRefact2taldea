package interfaz_grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class usuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String opciontabla;
	Statement st;
	ResultSet rs;
	private JTable table;
	
	Inicio ini = new Inicio();
	String ip = ini.ip;
	String BBDD = ini.BBDD;
	String usuario=ini.usuario;
	String contraseña=ini.contraseña;
	
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
			Connection conexion = DriverManager.getConnection (ip,usuario,contraseña);
			//Preparamos la consulta
			DefaultTableModel model = new DefaultTableModel();
			st = conexion.createStatement();
			rs = st.executeQuery ("select * from "+opciontabla);
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	public usuario() {
		
		setResizable(false);
		setTitle("Base de datos de usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"langileak", "konponenteak", "hornitzaileak", "bezeroak", "berriak"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setBounds(10, 11, 104, 22);
		contentPane.add(comboBox_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(124, 11, 739, 410);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton cargar = new JButton("Cargar base");
		cargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciontabla = comboBox_1.getSelectedItem().toString();
				actualizartabla(opciontabla);
			}	
		});
		cargar.setHorizontalAlignment(SwingConstants.LEFT);
		cargar.setFont(new Font("Tahoma", Font.BOLD, 11));
		cargar.setBounds(10, 41, 104, 23);
		contentPane.add(cargar);
		
		opciontabla = comboBox_1.getSelectedItem().toString();
		actualizartabla(opciontabla);
	}
	
}

