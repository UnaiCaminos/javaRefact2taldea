package factura;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLToPDF {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            
            String url = "jdbc:mysql://192.168.115.2:3306/erronka2";
            String usuario = "2taldea";
            String contraseña = "2taldea@";
            connection = DriverManager.getConnection(url, usuario, contraseña);

            String query = "SELECT nan, izena, abizena1, prezioTotala FROM saskia "
            		+ "CROSS JOIN bezeroak"
            		+ " ON saskia.nan_bezeroa = bezeroak.nan; "
            		+ "SELECT konponenteMota, modelo, marka, kopurua FROM produktueskaera"
            		+ "            		 CROSS JOIN konponenteak"
            		+ "            		 ON produktueskaera.idProduktua = konponenteak.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Pdf-ak gordetzen diren karpeta zehaztu
            String dok = "/ C://Users//usuario//eclipse-workspace//javaRefact2taldea//dokumentuak/";
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
                String columna1 = resultSet.getString("columna1");
                String columna2 = resultSet.getString("columna2");
                String columna3 = resultSet.getString("columna3");
                String columna4 = resultSet.getString("columna4");
                String columna5 = resultSet.getString("columna5");
                String columna6 = resultSet.getString("columna6");
                String columna7 = resultSet.getString("columna7");
                String columna8 = resultSet.getString("columna8");

                
                document.add(new Paragraph("Columna1: " + columna1));
                document.add(new Paragraph("Columna2: " + columna2));
                document.add(new Paragraph("Columna3: " + columna3));
                document.add(new Paragraph("Columna4: " + columna4));
                document.add(new Paragraph("Columna5: " + columna5));
                document.add(new Paragraph("Columna6: " + columna6));
                document.add(new Paragraph("Columna7: " + columna7));
                document.add(new Paragraph("Columna8: " + columna8));
                document.add(new Paragraph("\n"));  // Erregistroen arteko saltoak
            }
            
            document.close();
            connection.close();

            System.out.println("PDF creado con éxito en la carpeta: " + dok);

        } catch (SQLException | DocumentException e) {
            e.printStackTrace();
        }
    }
}

