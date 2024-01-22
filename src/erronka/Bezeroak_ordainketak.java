package erronka;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Bezeroak_ordainketak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtData;
	private JTextField txtData2;
	private JButton btnKontagailua;
	private JButton btnOrdainketa;
	private JButton btnOrdenatu;

	public Bezeroak_ordainketak() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 751, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTaula = new JButton("Taula");
		btnTaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM bezeroak_ordainketak";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Data");
				model.addColumn("Ordainketa egoera");
				model.addColumn("Ordainketa metodoa");
				model.addColumn("Ordinketa kantitatea");
				model.addColumn("Eskaeren egoera");
				
				table.setModel(model);
				String[] array = new String[6];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						//System.out.println(rs.getString(2));
						array[0]=rs.getString(1);
						array[1]=rs.getString(2);
						array[2]=rs.getString(3);
						array[3]=rs.getString(4);
						array[4]=rs.getString(5);
						array[5]=rs.getString(6);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTaula.setBounds(0, 11, 89, 23);
		contentPane.add(btnTaula);
		
		table = new JTable();
		table.setBounds(191, 15, 534, 287);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("Ordainketa info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data, data2;
				data=txtData.getText();
				data2=txtData2.getText();
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM bezeroak_ordainketak WHERE Data between'"+data+"' AND '"+data2+"'";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Data");
				model.addColumn("Ordainketa egoera");
				model.addColumn("Ordainketa metodoa");
				model.addColumn("Ordinketa kantitatea");
				model.addColumn("Eskaeren egoera");
				
				table.setModel(model);
				String[] array = new String[6];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						//System.out.println(rs.getString(2));
						array[0]=rs.getString(1);
						array[1]=rs.getString(2);
						array[2]=rs.getString(3);
						array[3]=rs.getString(4);
						array[4]=rs.getString(5);
						array[5]=rs.getString(6);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(0, 37, 161, 23);
		contentPane.add(btnNewButton);
		btnKontagailua = new JButton("Ordaindu dutenak");
		btnKontagailua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT COUNT(Izena) FROM bezeroak_ordainketak WHERE Ordainketa_egoera=true";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Kount");
				
				table.setModel(model);
				String[] array = new String[1];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						//System.out.println(rs.getString(2));
						array[0]=rs.getString(1);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnKontagailua.setBounds(0, 133, 161, 23);
		contentPane.add(btnKontagailua);
		btnOrdainketa = new JButton("Ordainketa metodoa");
		btnOrdainketa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT MAX(Ordainketa_kantitatea) FROM db1.bezeroak_ordainketak GROUP BY Ordaiketa_metodoa";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("1");
				
				table.setModel(model);
				String[] array = new String[1];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						array[0]=rs.getString(1);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOrdainketa.setBounds(0, 157, 156, 23);
		contentPane.add(btnOrdainketa);
		
		btnOrdenatu = new JButton("Ordenatu");
		btnOrdenatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM db1.bezeroak_ordainketak ORDER BY Ordainketa_kantitatea ASC";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("1");
				model.addColumn("2");
				model.addColumn("3");
				model.addColumn("4");
				model.addColumn("5");
				model.addColumn("6");
				
				table.setModel(model);
				String[] array = new String[7];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						array[0]=rs.getString(1);
						array[1]=rs.getString(2);
						array[2]=rs.getString(3);
						array[3]=rs.getString(4);
						array[4]=rs.getString(5);
						array[5]=rs.getString(6);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOrdenatu.setBounds(0, 179, 89, 23);
		contentPane.add(btnOrdenatu);
		
		txtData = new JTextField();
		txtData.setText("aaaa-mm-dd");
		txtData.setBounds(3, 88, 86, 20);
		contentPane.add(txtData);
		txtData.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ordainketa data:");
		lblNewLabel.setBounds(10, 60, 109, 23);
		contentPane.add(lblNewLabel);
		
		txtData2 = new JTextField();
		txtData2.setText("aaaa-mm-dd");
		txtData2.setBounds(3, 109, 86, 20);
		contentPane.add(txtData2);
		txtData2.setColumns(10);
		
	}
}
