package Utilidades;

import java.io.*;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Base.ClaseBase;


public class GenerarReportePdf {

	
		static String nombre="prueba";
		static String fecha;
		static Document documento;
		static FileOutputStream archivo;
		static Paragraph titulo;
		String rutaImagen;
		String hora;
		String horaIni,horaFin;
			
		
		public void setRutaImagen(String rutaImagen) 
		{
			this.rutaImagen = rutaImagen;
		}
		
		public void crearPlantilla(String nomTest, File rutaCarpeta) 
		{
			// INSTANCIAR DOCUMENTO
			documento = new Document();
			
			//TOMAR LA HORA DEL SISTEMA
			hora =  ClaseBase.fechaHora();
			horaIni = ClaseBase.fechaHora2();
			
			try 
			{
				//CREAR RUTA Y NOMBRE DEL PDF	
				archivo = new FileOutputStream(rutaCarpeta+"\\"+"Reporte-"+nomTest+"-"+ hora+".pdf");
				PdfWriter.getInstance(documento, archivo);
				
				//****CREAR ENCABEZADO
				//UBICACION DE LA IMAGEN
				Image header = Image.getInstance(rutaImagen);
				//TAMANO DE LA IMAGEN
				header.scaleToFit(100,200);
				//Header.SetAlignment(Chunk.ALIGN_CENTER);
				header.setWidthPercentage(25);
				
				//CREAR TITULO DEL PDF
				titulo = new Paragraph (nomTest+"\n\n"+"Fecha inicio: "+horaIni);
				titulo.setAlignment(1);
				
				//CREAR TABLA DE ENCABEZADO
				PdfPTable table = new PdfPTable(2);
				table.setWidthPercentage(100);
				
				PdfPCell pos1= new PdfPCell(header);
				pos1.setHorizontalAlignment(1);
				pos1.setVerticalAlignment(2);
				
				PdfPCell pos2= new PdfPCell(titulo);
				pos2.setHorizontalAlignment(1);
				pos2.setVerticalAlignment(2);
				
				table.addCell(pos2);
				table.addCell(pos1);
				
				// Establecer márgenes en milímetros (izquierda, derecha, superior, inferior)
				documento.setMargins(30, 30, 30, 30);
				//ABRIR DOCUMENTO
				documento.open();
				
				//INSERTA LA IMAGEN
				documento.add(table);
				
				documento.add(Chunk.NEWLINE);
				
			} 
			catch(FileNotFoundException e)
			{
			 System.err.println(e.getMessage());
			}
			catch(DocumentException e)
			{
			 System.err.println(e.getMessage());
			}
			catch(IOException e)
			{
			 System.err.println("Error al logo periferia: "+e.getMessage());
			}
			
		}
		
		public void crearbody(By locator, String rutaImagen) throws  DocumentException, MalformedURLException, IOException
		{
			//OBTENER EL NOMBRE DEL LOCALIZADOR
			String locator1=locator.toString();
			
			//DAR FORMATO A LA FUENTE
			Paragraph parrafo = new Paragraph();
			parrafo.setAlignment(Chunk.ALIGN_LEFT);
			parrafo.setFont(FontFactory.getFont("Arial",10,Font.NORMAL));
			parrafo.add("Se realiza accion sobre el elemento: "+locator1);
			
			//ADICIONAR MENSAJE AL PDF
			documento.add(parrafo);
			
			//INSERT IMAGEN
			//UBICACION DE LA IMAGEN
			Image imagen = Image.getInstance(rutaImagen);
			imagen.setBorderColor(BaseColor.BLACK);
			
			//TAMANO DE LA IMAGEN
			imagen.scaleToFit(500,800);
			//imagen.scaleToFit(700,1000);
			imagen.setAlignment(Chunk.ALIGN_CENTER);
			documento.add(imagen);
		}
		
		public void crearbodyError(By locator, String rutaImagen, String msnError) throws  DocumentException, MalformedURLException, IOException
		{
			int imagenesAgregadasEnPaginaActual = 0;
			
			//OBTENER EL NOMBRE DEL LOCALIZADOR
			String locator1=locator.toString();
			//DAR FORMATO A LA FUENTE
			Paragraph parrafo = new Paragraph();
			parrafo.setAlignment(Chunk.ALIGN_LEFT);
			parrafo.setFont(FontFactory.getFont("Arial",10,Font.NORMAL));
			parrafo.add("\n Se realiza accion sobre el elemento: "+locator1);
			
			//ADICIONAR MENSAJE AL PDF
			documento.add(parrafo);
			
			//INSERT IMAGEN
			//UBICACION DE LA IMAGEN
			Image imagen = Image.getInstance(rutaImagen);
			imagen.setBorderColor(BaseColor.BLACK);
			
			//TAMANO DE LA IMAGEN
			imagen.scaleToFit(500,800);
			imagen.setAlignment(Chunk.ALIGN_LEFT);
			
			if (imagenesAgregadasEnPaginaActual >= 2) {
		        // Si se alcanza el límite, comenzar una nueva página aquí
		        documento.newPage();
		        imagenesAgregadasEnPaginaActual = 0; // Reiniciar el contador para la nueva página
		    }
			documento.add(imagen);
			imagenesAgregadasEnPaginaActual++;
			
			//MENSAJE ERROR
			//DAR FORMATO A LA FUENTE
			Paragraph parrafoError = new Paragraph();
			parrafoError.setAlignment(Chunk.ALIGN_LEFT);
			parrafoError.setFont(FontFactory.getFont("Arial",8,Font.NORMAL));
			parrafoError.add("EL MENSAJE DE ERROR:  "+"\n"+msnError);
			documento.add(parrafoError);
		}
		
		public void cerrarPlantilla() throws DocumentException
		{
			documento.add(Chunk.NEWLINE);
			//DAR FORMATO A LA FUENTE
			Paragraph parrafo = new Paragraph();
			parrafo.setAlignment(Chunk.ALIGN_RIGHT);
			parrafo.setFont(FontFactory.getFont("Arial",8,Font.BOLD));
			parrafo.add("Fecha inicio:  "+horaIni+"\n");
			
			//ADICIONAR MENSAJE AL PDF
			//documento.add(parrafo);
			horaFin = ClaseBase.fechaHora2();
			
			// Agregar tu texto estático
			Paragraph espacioEnBlanco = new Paragraph(" ");
		    espacioEnBlanco.setSpacingAfter(10f);
		    parrafo.add("Alexander Sanchez\n Ingeniero QA\n");
			
			//ADICIONAR MENSAJE AL PDF
		    documento.add(espacioEnBlanco);
			documento.add(parrafo);
			documento.close();
			
		}
}
