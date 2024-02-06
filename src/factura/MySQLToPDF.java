package factura;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import erronka.konexioaLocal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MYSQLToPDF extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnAktualizatu;
	private JTextField txtId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MYSQLToPDF frame = new MYSQLToPDF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		        Connection connection = null;

		        try {
		            
		            String url = "jdbc:mysql://192.168.115.2:3306/erronka2";
		            String usuario = "2taldea";
		            String contraseña = "2taldea@";
		            connection = DriverManager.getConnection(url, usuario, contraseña);

		            String query = "SELECT nan, izena, abizena1, prezioTotala, konponenteMota, modelo, marka, kopurua"
		            		+ "FROM saskia"
		            		+ "JOIN bezeroak ON saskia.nan_bezeroa = bezeroak.nan"
		            		+ "JOIN produktueskaera ON saskia.id_eskaera = produktueskaera.idEskaera"
		            		+ "JOIN konponenteak ON produktueskaera.idProduktua = konponenteak.id";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            ResultSet resultSet = preparedStatement.executeQuery();

		            // Pdf-ak gordetzen diren karpeta zehaztu
		            String dok = "..//javaRefact2taldea//dokumentuak/";
		            File carpeta = new File(dok);

		            // Karpeta espezifikatuan pdf-a sortu
		            String pdf = "faktura.pdf";
		            Document document = new Document();
		            try {
						PdfWriter.getInstance(document, new FileOutputStream(dok + File.separator + pdf));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            document.open();

		            //Datuak pdf-an gehitu
		            while (resultSet.next()) {
		                String nan = resultSet.getString("nan");
		                String izena = resultSet.getString("izena");
		                String abizena1 = resultSet.getString("abizena1");
		                String prezioTotala = resultSet.getString("prezioTotala");
		                String konponenteMota = resultSet.getString("mota");
		                String modelo = resultSet.getString("modeloa");
		                String marka = resultSet.getString("marka");
		                String kopurua = resultSet.getString("kopurua");
		                // Resto de las variables de la primera consulta

		                document.add(new Paragraph("NAN: " + nan));
		                document.add(new Paragraph("Izena: " + izena));
		                document.add(new Paragraph("Abizena1: " + abizena1));
		                document.add(new Paragraph("Prezio Totala: " + prezioTotala));
		                document.add(new Paragraph("Produktua: " + konponenteMota));
		                document.add(new Paragraph("Modeloa: " + modelo));
		                document.add(new Paragraph("Marka: " + marka));
		                document.add(new Paragraph("Kopurua: " + kopurua));
		                // Resto de las columnas de la primera consulta
		                document.add(new Paragraph("\n"));
		            }
		            
		            document.close();
		            connection.close();

		            System.out.println("PDF creado con éxito en la carpeta: " + dok);
		            
		        } catch (SQLException | DocumentException e) {
		            e.printStackTrace();
		        }
		    }
	
	public MYSQLToPDF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTaula = new JButton("Taula");
		btnTaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				konexioa kon=new konexioa();
				Connection conexion = kon.getConnection();
				String sql = "SELECT * FROM erronka2.saskia";
				Statement st;
				ResultSet rs;
				
				DefaultTableModel model =new DefaultTableModel();
				
				model.addColumn("id_eskaera");
				model.addColumn("nan_bezeroa");
				model.addColumn("Segimentua");
				model.addColumn("prezioTotala");
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
		btnTaula.setBounds(10, 24, 112, 23);
		contentPane.add(btnTaula);
		
		table = new JTable();
		table.setBounds(146, 14, 406, 441);
		contentPane.add(table);
		
		btnAktualizatu = new JButton("Aktualizatu");
		btnAktualizatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    konexioa kon=new konexioa();
				    Connection conexion = kon.getConnection();
				    String id;
				    id=txtId.getText();
				    String query ="UPDATE erronka2.saskia SET segimentua = BIDALIA WHERE id_eskaera ="+id;
				    Statement st;
					st = conexion.createStatement();
					st .executeUpdate(query);
					showMessageDialog(null, "Datuak ondo aldatu dira");
				} catch (SQLException e1) {
					showMessageDialog(null, "Arazoa aktualizatzean.");
					e1.printStackTrace();
				}
			}
		});
		btnAktualizatu.setBounds(10, 49, 112, 23);
		contentPane.add(btnAktualizatu);
		
		txtId = new JTextField();
		txtId.setText("Eskariaren id");
		txtId.setColumns(10);
		txtId.setBounds(11, 73, 125, 20);
		contentPane.add(txtId);
	}
}
