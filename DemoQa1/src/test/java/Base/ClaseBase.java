package Base;



import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Utilidades.GenerarReportePdf;

public class ClaseBase {
	
	protected static WebDriver driver;
	
	public ClaseBase(WebDriver driver) {
		super();
	}
	
	//METODO NAVEGADOR
	public static WebDriver chromeDriverConnetion()
	{
		//SETEAR LAS OPCIOENS DE NAVEGADOR
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		//SETEAR LAS PROPIEDADES DEL NAVEGADOR
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		// MAXIMIZAR NAVEGADOR
		driver.manage().window().maximize();
		return driver;
	}
	
	//MOTODO PARA HACER CLICK
	public void click(By locator, File rutaCarpeta, int tiempo, String evidencia) throws Exception 
	{
		capturaScreen(rutaCarpeta, locator, evidencia);
		driver.findElement(locator).click();
		tiempoEspera(tiempo);
		
	}
	
	//MOTODO PARA BORRAR
	public void borrar(By locator, File rutaCarpeta, String evidencia ) throws Exception 
	{
		driver.findElement(locator).clear();
		capturaScreen(rutaCarpeta, locator, evidencia);
		
	}
	//MOTODO PARA ENVIAR TEXTO
	public void sendKey(String inputText,By locator, File rutaCarpeta, String evidencia) throws Exception 
	{
		
		driver.findElement(locator).sendKeys(inputText);
		capturaScreen(rutaCarpeta, locator, evidencia);
	}
	
	//MOTODO PARA ENTER
	public void submit(By locator, File rutaCarpeta, String evidencia) throws Exception 
	{
		driver.findElement(locator).submit();	
		capturaScreen(rutaCarpeta, locator, evidencia);
	}
	
	//METODO DE ESPERA
	public void tiempoEspera(int tiempo) throws InterruptedException
	{
		Thread.sleep(tiempo*1000);
	}
	
	//METODO PARA TOMARLA FECHADEL SISTEMA
	public static String fechaHora()
	{
		//SE TOMA LA FECHADEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		//DEFINIMOS EL FORMATO QUE QUEREMOS PARALA FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHMMss");
		
		//DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}
	//METODO PARA TOMARLA FECHADEL SISTEMA
	public static String fechaHora2()
	{
		//SE TOMA LA FECHADEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		//DEFINIMOS EL FORMATO QUE QUEREMOS PARALA FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:ss");
		//DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}
	
	public void eliminarArchivo(String rutaImagen)
	{
		File fichero = new File(rutaImagen);
		fichero.delete();
	}
	
	//METODO PARA TOMAR LA HORA DEL SISTEMA
	public String HoraSistema()
	{
		//TOMAMOS LA HORA DEL SISTEMA
		LocalTime horaSistema =LocalTime.now();
		//DEFINIR EL FORMATO DE LA HORA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("HHmmss");
		//DAR FORMATO A LA HORA DEL SISTEMA
		String hora = fecha.format(horaSistema);
		return hora;
	}
	
	//METODO PARA CAPTURAR LA IMAGEN DE LA PANTALLA
	public void capturaScreen(File rutaCarpeta, By locator, String evidencia) throws Exception
	{
		if(evidencia.equals("Si"))
		{
			//TOMAMOS LA HORA DEL SISTEMA
			String hora = HoraSistema();
			//TOMAMOS CAPTURA DE LA PANTALLA Y LO ALMACENAMOS EN UNA VARIABLE
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			/*CON EL SCREN TOMADAO LE INDICAMOS QUE LO COPIEY LO ASIGNE A LA RUTA DE LA CARPETA CON EL 
			NOMBRE LA HORA Y FORMATO DE LA IMAGEN*/
			FileUtils.copyFile(scrFile, new File(rutaCarpeta+"\\"+hora+".png"));
			String rutaImagen = new File(rutaCarpeta+"\\"+hora+".png").toString();
			
			//INSTANCIAR LA CLASE GENERAR PDF
			GenerarReportePdf informePdf = new GenerarReportePdf();
			//SE PROCEDE A INSERTAR LOCALIZADOR HE IMAGEN EN EL PDF
			informePdf.crearbody(locator, rutaImagen);
			
			eliminarArchivo(rutaImagen);
		}
		
	}
	
	//METODO PARACREAR LA CARPETA
	public File crearCarpeta(Properties propiedades, String nomTest)
	{
		//ALMACENAMOS LA FECHA DEL SISTEMA
		String fecha = fechaHora();
		//CREAMOS EL NOMBRE DEL LA CARPETA
		String nomCarpeta = nomTest+"-"+fecha;
		//OBTENEMOS LA RUTA DE ALOJAMIENTO DE SALIDA Y EL NOMBRE DEL TEST A EJECUTAR
		File directorio = new File("./output/"+nomCarpeta);
		//CREAMOS LA CARPETA
		directorio.mkdir();
		return directorio;
	}
	


	

	
	


		
	
	

}
