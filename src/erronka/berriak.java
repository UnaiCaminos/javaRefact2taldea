package erronka;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;

public class berriak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTable table;
	private JTextField txtIzenburua;
	private JTextField txtDeskribapena;
	private JTextField txtData;
	private JTextField txtId2;

	public berriak() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 541);
		getContentPane().setLayout(null);
		
		JButton btnTaula = new JButton("Taula");
		btnTaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM berriak";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("id");
				model.addColumn("Izenburua");
				model.addColumn("Deskribapena");
				model.addColumn("Data");
				
				table.setModel(model);
				String[] array = new String[4];
				try {
					st=conexion.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						//System.out.println(rs.getString(2));
						array[0]=rs.getString(1);
						array[1]=rs.getString(2);
						array[2]=rs.getString(3);
						array[3]=rs.getString(4);
						model.addRow(array);
						
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTaula.setBounds(10, 10, 101, 21);
		getContentPane().add(btnTaula);
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setColumns(10);
		txtId.setBounds(10, 58, 86, 20);
		getContentPane().add(txtId);
		
		table = new JTable();
		table.setBounds(141, 13, 379, 353);
		getContentPane().add(table);
		
		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String izn,deskrb,id,data;
				    izn=txtIzenburua.getText();
				    deskrb=txtDeskribapena.getText();
				    data=txtData.getText();
				    id=txtId.getText();
				    String query ="INSERT INTO erronka.berriak"
						+ " Values('"+id+"', '"+izn+"', '"+deskrb+"', '"+data+"')";
				    Statement stmt;
					stmt = conexion.createStatement();
					stmt .executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Datuak ondo gehitu dira");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Datuak ezin dira gehitu");
					e1.printStackTrace();
				}
			}
		});
		btnGehitu.setBounds(10, 35, 101, 21);
		getContentPane().add(btnGehitu);
		
		txtIzenburua = new JTextField();
		txtIzenburua.setText("izenburua");
		txtIzenburua.setColumns(10);
		txtIzenburua.setBounds(10, 82, 86, 20);
		getContentPane().add(txtIzenburua);
		
		txtDeskribapena = new JTextField();
		txtDeskribapena.setText("deskribapena");
		txtDeskribapena.setColumns(10);
		txtDeskribapena.setBounds(10, 105, 86, 20);
		getContentPane().add(txtDeskribapena);
		
		txtData = new JTextField();
		txtData.setText("data");
		txtData.setColumns(10);
		txtData.setBounds(10, 129, 86, 20);
		getContentPane().add(txtData);
		
		JButton btnKendu = new JButton("Kendu");
		btnKendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String id;
				    id=txtId2.getText();
				    String query ="DELETE FROM erronka.berriak WHERE id= "+id;
				    Statement st;
					st = conexion.createStatement();
					st.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Datuak ondo ezabatu dira");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Datuak ezin dira ezabatu");
					e1.printStackTrace();
				}
			}
		});
		btnKendu.setBounds(10, 154, 101, 21);
		getContentPane().add(btnKendu);
		
		txtId2 = new JTextField();
		txtId2.setText("id");
		txtId2.setColumns(10);
		txtId2.setBounds(10, 178, 86, 20);
		getContentPane().add(txtId2);
	}
}
