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
import javax.swing.SwingConstants;

public class Hornitzaileak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnTaula;
	private JTextField txtLetra;
	private JButton btnGehitu;
	private JTextField txtIzena;
	private JTextField txtKokapena;
	private JTextField txtTelefonoa;
	private JTextField txtKorreoa;
	private JTextField txtId;
	private JButton btnNewButton;
	private JTextField txtName;
	private JTextField txtId2;

	public Hornitzaileak() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 540, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLike = new JButton("Bilatu");
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String like;
				like=txtLetra.getText();
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM hornitzaile WHERE Izena like'"+like+"%'";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Kokapena");
				model.addColumn("Telefonoa");
				model.addColumn("korreo_elektronikoa");
				model.addColumn("ID");
				
				table.setModel(model);
				String[] array = new String[5];
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
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLike.setBounds(0, 63, 89, 23);
		contentPane.add(btnLike);
		
		table = new JTable();
		table.setBounds(135, 0, 379, 353);
		contentPane.add(table);
		
		btnTaula = new JButton("Taula");
		btnTaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM hornitzaile";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("Izena");
				model.addColumn("Kokapena");
				model.addColumn("Telefonoa");
				model.addColumn("korreo_elektronikoa");
				model.addColumn("ID");
				
				table.setModel(model);
				String[] array = new String[5];
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
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTaula.setBounds(0, 29, 89, 23);
		contentPane.add(btnTaula);
		
		btnGehitu = new JButton("Gehitu");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String izn,tel,kokapena,korreoa,Id;
				    izn=txtIzena.getText();
				    kokapena=txtKokapena.getText();
				    tel=txtTelefonoa.getText();
				    korreoa=txtKorreoa.getText();
				    Id=txtId.getText();
				    String query ="INSERT INTO db1.hornitzaile"
						+ " Values('"+izn+"', '"+kokapena+"', '"+tel+"', '"+korreoa+"', '"+Id+"')";
				    Statement stmt;
				
					stmt = conexion.createStatement();
					stmt .executeUpdate(query);
					System.out.println("Usuario bat gehitu duzu");
				} catch (SQLException e1) {
					System.out.println("Error!!!");
					e1.printStackTrace();
				}
			}
		});
		btnGehitu.setBounds(0, 124, 89, 23);
		contentPane.add(btnGehitu);
		
		btnNewButton = new JButton("Kendu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String izena, Id2;
				    izena=txtName.getText();
				    Id2=txtId2.getText();
				    String query ="DELETE FROM db1.hornitzaile WHERE Izena= '"+izena+"'&& Id= '"+Id2+"'";
				    Statement st;
					st = conexion.createStatement();
					st.executeUpdate(query);
					System.out.println("Usuario bat ezabatu duzu");
				} catch (SQLException e1) {
					System.out.println("Error!!!");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(0, 310, 89, 23);
		contentPane.add(btnNewButton);
		
		txtLetra = new JTextField();
		txtLetra.setText("Sartu  lehenengo letra(k)");
		txtLetra.setBounds(0, 92, 152, 23);
		contentPane.add(txtLetra);
		txtLetra.setColumns(10);
		
		txtIzena = new JTextField();
		txtIzena.setText("Izena");
		txtIzena.setBounds(0, 158, 86, 20);
		contentPane.add(txtIzena);
		txtIzena.setColumns(10);
		
		txtKokapena = new JTextField();
		txtKokapena.setText("Kokapena");
		txtKokapena.setBounds(0, 189, 86, 20);
		contentPane.add(txtKokapena);
		txtKokapena.setColumns(10);
		
		txtTelefonoa = new JTextField();
		txtTelefonoa.setText("Telefonoa");
		txtTelefonoa.setBounds(0, 221, 86, 20);
		contentPane.add(txtTelefonoa);
		txtTelefonoa.setColumns(10);
		
		txtKorreoa = new JTextField();
		txtKorreoa.setText("Korreoa");
		txtKorreoa.setBounds(0, 252, 86, 20);
		contentPane.add(txtKorreoa);
		txtKorreoa.setColumns(10);
		
		txtId = new JTextField();
		txtId.setText("Id");
		txtId.setBounds(0, 283, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		
		
		txtName = new JTextField();
		txtName.setText("Izena");
		txtName.setBounds(0, 343, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtId2 = new JTextField();
		txtId2.setText("Id");
		txtId2.setBounds(0, 367, 86, 20);
		contentPane.add(txtId2);
		txtId2.setColumns(10);
	}
}
