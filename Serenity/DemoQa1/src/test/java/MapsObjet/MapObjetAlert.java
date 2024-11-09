package MapsObjet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.ClaseBase;

public class MapObjetAlert extends ClaseBase {

	public MapObjetAlert(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	protected By BtnAlert = By.xpath("(//*[name()='svg'][@stroke='currentColor'])[3]");
	protected By Alerts = By.xpath("(//li[@id='item-1'])[2]");
	
	protected By acceptAlert = By.xpath("//button[@id='alertButton']");
	protected By timeAlert = By.xpath("//button[@id='timerAlertButton']");
	protected By confAlert = By.xpath("//button[@id='confirmButton']");
	protected By promtAlert = By.xpath("//button[@id='promtButton']");
	
	
	///CAKLENDAR
	protected By Home =  By.xpath("//img[@src='/images/Toolsqa.jpg']");

	protected By Widget =  By.xpath("(//div[@class='card-up'])[4]");
	protected By dataPicker =  By.xpath("(//li[@id='item-2'])[3]");
	
	//PRIMER CALENDARIO
	protected By calendarOne = By.xpath("//input[@id='datePickerMonthYearInput']");
	protected By contenedor = By.id("datePickerMonthYearInput");
 
 	
	protected By calendarTwo = By.xpath("//input[@id='dateAndTimePickerInput']");
}
