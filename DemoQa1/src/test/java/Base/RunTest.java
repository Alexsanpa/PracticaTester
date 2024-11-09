package Base;
import org.testng.annotations.Test;

import PagsObjet.PagObjetAlert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;


import Utilidades.ExcelUtilidades;
import Utilidades.GenerarReportePdf;
import Utilidades.MyScreenRecorder;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class RunTest {
	
	private WebDriver driver;
	PagObjetAlert pagAlert;
	ClaseBase claseBase;
	Properties propiedades;
	
	//CREAR VARIABLES
	String rutaCarpeta;
	GenerarReportePdf generarReportePdf;

	@BeforeClass
	public void beforeClass() throws Exception 
	{
		//INSTANCIAR LA CLASE PROPERTIES
		propiedades = new Properties();
		//INSTANCIAR LA CLASE PAGOBJET
		pagAlert = new PagObjetAlert(driver);
		generarReportePdf = new GenerarReportePdf();
		
		//INSTANCIAMOS LA CARPETA DE PROEPERTIES
		Properties propiedades = new Properties();
		claseBase = new ClaseBase(driver);
		
		//CREAMOS LA VARIABLE TIPO INPUTSTRING
		InputStream entrada = null;
		
		//CREAMOS UN TRY CATCH PARAVALIDAR SI NOS GENERA UN ERROR AL MOMENTOP DE BUSCAR EL ARCHIVO
		try
		{
			entrada = new FileInputStream("./Properties/data.properties");
			propiedades.load(entrada);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
			
		//ASIGNAR LAS OPCIONES Y LA CONFIGURACION DEL NAVEGADOR A LA VARIABLE DRIVER
		driver = pagAlert.chromeDriverConnetion();
		
		//ACCEDEMOS AL METODO URL Y CON PROPERTIES LE INDICAMOS QUE CAPTURE LA URL QUE CORRESPONDE CONE SE NOMBRE
		pagAlert.urlAcceso(propiedades.getProperty("urlDemoQa"));
		rutaCarpeta = (propiedades.getProperty("outputFile"));
		//capturar ubicacion de imagen para el reporte
		generarReportePdf.setRutaImagen(propiedades.getProperty("rutaImagenReporte"));
	}
	
  @DataProvider(name="mensaje")
  public Object[][] datos() throws Exception
  {
	  Object[][] arreglo = ExcelUtilidades.getTableArray("./Data/DemoQa.xlsx", "Mensaje" );
      return arreglo;
  }
	  
  @Test(dataProvider = "mensaje")
  public void pruebasIniciales(String Message, String Fecha, String Fecha2, String Ejecutar, String evidencia) throws Exception 
  {
	  String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
	  @SuppressWarnings("static-access")
	  File rutaCarpeta = claseBase.crearCarpeta(propiedades,nomTest);
	  
	  if(Ejecutar.equals("Si")) 
		  
	  {
		  //iniciar reporte PDF-Video
		  generarReportePdf.crearPlantilla(nomTest, rutaCarpeta);
		  MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
		  
		  pagAlert.demoAlerts(Message, rutaCarpeta, evidencia);
		  pagAlert.calendar(Fecha, Fecha2, rutaCarpeta, evidencia);
		  
		  //Finaliza reporte PDF- Video
		  generarReportePdf.cerrarPlantilla();
		  MyScreenRecorder.stopRecording();
	  }
  }


  @AfterClass
  public void afterClass() 
  {
	  driver.close();
  }

}
