package EndtoEnd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Thankyoupage

{
	WebDriver dr;
	public Thankyoupage(WebDriver dr) 
	{
		this.dr=dr;
	}
	
	By orderplacedmessage =By.cssSelector(".hero-primary");
	
	public String orderplacedmessage()
	{
	return dr.findElement(orderplacedmessage).getText();
	
	}
}
