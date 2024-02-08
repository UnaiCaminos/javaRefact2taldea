package erronkaServer;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

public class Hornitzaileak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnTaula;
	private JTextField txtLetra;
	private JButton btnGehitu;
	private JTextField txtIzena;
	private JTextField txtHelbidea;
	private JTextField txtTelefonoa;
	private JTextField txtKorreoa;
	private JTextField txtId;
	private JButton btnNewButton;
	private JTextField txtName;
	private JTextField txtId2;
	private JTextField txtNanNif;
	private JTextField txtTestua;

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
				konexioaServer kon=new konexioaServer();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM hornitzaileak WHERE EnpresarenIzena like'"+like+"%'";
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
					showMessageDialog(null, "Konexio arazoak");
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
				konexioaServer kon=new konexioaServer();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM hornitzaileak";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("id");
				model.addColumn("Telefonoa");
				model.addColumn("Enpresa");
				model.addColumn("korreo_elektronikoa");
				model.addColumn("Helbidea");
				model.addColumn("NAN");
				model.addColumn("Testua");
				
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
		btnTaula.setBounds(0, 29, 89, 23);
		contentPane.add(btnTaula);
		
		btnGehitu = new JButton("Gehitu");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioaServer kon=new konexioaServer();
				    Connection conexion = kon.getConnection();
				    String izn,tel,helbidea,Id,korreoa,nan,testua;
				    izn=txtIzena.getText();
				    helbidea=txtHelbidea.getText();
				    tel=txtTelefonoa.getText();
				    korreoa=txtKorreoa.getText();
				    Id=txtId.getText();
				    nan=txtNanNif.getText();
				    testua=txtTestua.getText();
				    String query ="INSERT INTO erronka2.hornitzaileak"
						+ " Values('"+Id+"', '"+tel+"', '"+izn+"', '"+korreoa+"', '"+helbidea+"', '"+nan+"', '"+testua+"')";
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
		btnGehitu.setBounds(0, 124, 89, 23);
		contentPane.add(btnGehitu);
		
		btnNewButton = new JButton("Kendu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioaServer kon=new konexioaServer();
				    Connection conexion = kon.getConnection();
				    String izena, Id2;
				    izena=txtName.getText();
				    Id2=txtId2.getText();
				    String query ="DELETE FROM erronka2.hornitzaileak WHERE EnpresarenIzena= '"+izena+"'&& id= '"+Id2+"'";
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
		btnNewButton.setBounds(0, 311, 89, 23);
		contentPane.add(btnNewButton);
		
		txtLetra = new JTextField();
		txtLetra.setText("Sartu  lehenengo letra(k)");
		txtLetra.setBounds(0, 92, 152, 23);
		contentPane.add(txtLetra);
		txtLetra.setColumns(10);
		
		txtIzena = new JTextField();
		txtIzena.setText("Izena");
		txtIzena.setBounds(0, 196, 125, 20);
		contentPane.add(txtIzena);
		txtIzena.setColumns(10);
		
		txtHelbidea = new JTextField();
		txtHelbidea.setText("Helbidea");
		txtHelbidea.setBounds(0, 243, 125, 20);
		contentPane.add(txtHelbidea);
		txtHelbidea.setColumns(10);
		
		txtTelefonoa = new JTextField();
		txtTelefonoa.setText("Telefonoa");
		txtTelefonoa.setBounds(0, 173, 125, 20);
		contentPane.add(txtTelefonoa);
		txtTelefonoa.setColumns(10);
		
		txtKorreoa = new JTextField();
		txtKorreoa.setText("Korreoa");
		txtKorreoa.setBounds(0, 220, 125, 20);
		contentPane.add(txtKorreoa);
		txtKorreoa.setColumns(10);
		
		txtId = new JTextField();
		txtId.setText("Id");
		txtId.setBounds(0, 150, 125, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		
		
		txtName = new JTextField();
		txtName.setText("Izena");
		txtName.setBounds(3, 344, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtId2 = new JTextField();
		txtId2.setText("Id");
		txtId2.setBounds(3, 370, 86, 20);
		contentPane.add(txtId2);
		txtId2.setColumns(10);
		
		txtNanNif = new JTextField();
		txtNanNif.setText("NAN/NIF");
		txtNanNif.setBounds(0, 264, 125, 19);
		contentPane.add(txtNanNif);
		txtNanNif.setColumns(10);
		
		txtTestua = new JTextField();
		txtTestua.setText("Testua");
		txtTestua.setBounds(0, 285, 125, 19);
		contentPane.add(txtTestua);
		txtTestua.setColumns(10);
	}
}
