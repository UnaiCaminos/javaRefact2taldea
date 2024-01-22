package erronka;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Hornitzaileak_ordainketak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTable table = new JTable();
	private JButton btnOrdenatu;

	public Hornitzaileak_ordainketak() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		table.setBounds(211, 11, 553, 320);
		contentPane.add(table);
		
		JButton btnTaula = new JButton("Taula");
		btnTaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM hornitzaile_ordainketak";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Data");
				model.addColumn("Ordainketa egoera");
				model.addColumn("Ordainketa metodoa");
				model.addColumn("Ordinketa kantitatea");
				model.addColumn("ID");
				model.addColumn("NAN");
				
				table.setModel(model);
				String[] array = new String[7];
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
						array[6]=rs.getString(7);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTaula.setBounds(0, 7, 89, 23);
		contentPane.add(btnTaula);
		
		JButton btnMin = new JButton("Ordainketa min");
		btnMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT MIN(Ordainketa_kantitatea) FROM db1.hornitzaile_ordainketak GROUP BY Ordaiketa_metodoa";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("1");				
				
				table.setModel(model);
				String[] array = new String[4];
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
		btnMin.setBounds(0, 33, 190, 23);
		contentPane.add(btnMin);
		
		btnOrdenatu = new JButton("Ordenatu");
		btnOrdenatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM db1.hornitzaile_ordainketak ORDER BY Ordainketa_kantitatea ASC";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Data");
				model.addColumn("Ordainketa egoera");
				model.addColumn("Ordainketa metodoa");
				model.addColumn("Ordinketa kantitatea");
				model.addColumn("ID");
				model.addColumn("NAN");
				
				table.setModel(model);
				String[] array = new String[7];
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
						array[6]=rs.getString(7);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOrdenatu.setBounds(0, 60, 190, 23);
		contentPane.add(btnOrdenatu);
		
		JButton btnBatazbestekoa = new JButton("Bataz bestekoa");
		btnBatazbestekoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT AVG(Ordainketa_kantitatea) FROM db1.hornitzaile_ordainketak";
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
		btnBatazbestekoa.setBounds(0, 88, 190, 23);
		contentPane.add(btnBatazbestekoa);
		
		JButton btnNewButton = new JButton("Ordenatu dataren arabera");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT DISTINCT * FROM db1.hornitzaile_ordainketak ORDER BY Data DESC";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Data");
				model.addColumn("Ordainketa egoera");
				model.addColumn("Ordainketa metodoa");
				model.addColumn("Ordinketa kantitatea");
				model.addColumn("ID");
				model.addColumn("NAN");
				
				table.setModel(model);
				String[] array = new String[7];
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
						array[6]=rs.getString(7);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(0, 113, 190, 23);
		contentPane.add(btnNewButton);
	}
}
