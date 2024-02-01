package erronka;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class facturaPrueba{

    
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        List<Persona> listaPersonas = new ArrayList<Persona>();
        
        Persona pesona = new Persona("Sergio P", "82342424");
        listaPersonas.add(pesona);
        
        Persona pesona1 = new Persona("Laura L", "4242424243");
        listaPersonas.add(pesona1);
        
        Persona pesona2 = new Persona("Fulanito P", "8672442324");
        listaPersonas.add(pesona2);
        
        System.out.println("LA LISTA TIENE: "+listaPersonas.size()+" agregadas");
        
        // LLAMADA AL METODO PARA CREAR EL PDF
        crearPDF(listaPersonas);
    }
    
    public static void crearPDF(List<Persona> lista) throws FileNotFoundException, DocumentException {
        // Se crea el documento
        Document documento = new Document();
        
        // El OutPutStream para el fichero donde crearemos el PDF
        FileOutputStream ficheroPDF = new FileOutputStream("Personas.pdf");
        
        // Se asocia el documento de OutPutStream
        PdfWriter.getInstance(documento, ficheroPDF);
        
        // Se abre el documento
        documento.open();
        
        // Parrafo
        Paragraph titulo = new Paragraph("Lista de personas \n\n",
                FontFactory.getFont("arial",
                        22,
                        Font.BOLD,
                        BaseColor.BLUE
                        )
        );
        
        // A�adimos el titulo al documento
        documento.add(titulo);
        
        // Creamos una tabla
        PdfPTable tabla = new PdfPTable(3);
        tabla.addCell("ID");
        tabla.addCell("NOMBRE");
        tabla.addCell("TELEFONO");
        
        for(int i = 0 ; i < lista.size() ; i++) {
            tabla.addCell("" + i);
            tabla.addCell(lista.get(i).getNombre());
            tabla.addCell(lista.get(i).getTelefono());
        }
        
        // A�adimos la tabla al documento
        documento.add(tabla);
        
        // Se cierra el documento
        documento.close();
    }
    
}
