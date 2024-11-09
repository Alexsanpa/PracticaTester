package PagsObjet;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;
import MapsObjet.MapObjetAlert;

public class PagObjetAlert extends MapObjetAlert{
	
	//ASIGNAR VARIABLE ALERT PARTA INTERACTUAR CON EL OBJETO Alert

	public PagObjetAlert(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//METODO INMICIAR URL
	public void urlAcceso(String url) 
	{
		driver.get(url);
	}
	
	public void demoAlerts(String Message,File rutaCarpeta, String evidencia)throws Exception
	{
		
		//INICIO
		click(BtnAlert, rutaCarpeta,1, evidencia);
		click(Alerts, rutaCarpeta,1, evidencia);
		
		
		click(acceptAlert, rutaCarpeta,1, evidencia);
		driver.switchTo().alert().accept();
		
		click(timeAlert, rutaCarpeta,6, evidencia);
		driver.switchTo().alert().accept();

		click(confAlert, rutaCarpeta,1, evidencia);
		driver.switchTo().alert().accept();
		
		
		click(promtAlert, rutaCarpeta,1, evidencia);
		driver.switchTo().alert().sendKeys(Message);
		driver.switchTo().alert().accept();
		tiempoEspera(4);
	
	}
	public void calendar(String Fecha, String Fecha2, File rutaCarpeta, String evidencia) throws Exception
	{
		click(Home,rutaCarpeta,1, evidencia);
		click(Widget, rutaCarpeta,1, evidencia);
		click(dataPicker, rutaCarpeta,1, evidencia);
		
		WebElement campoFecha = driver.findElement(contenedor);
		campoFecha.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		tiempoEspera(1);
		campoFecha.sendKeys(Keys.DELETE);
		sendKey(Fecha, contenedor, rutaCarpeta, evidencia);
		tiempoEspera(3);
		click(dataPicker,rutaCarpeta,1, evidencia);
		WebElement campoFecha2 = driver.findElement(calendarTwo);
		campoFecha2.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		tiempoEspera(1);
		campoFecha2.sendKeys(Keys.DELETE);
		sendKey(Fecha2, calendarTwo, rutaCarpeta, evidencia);
		tiempoEspera(3);
		
	}

	



}
