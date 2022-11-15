package EndtoEnd;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusablemethod
{
	
	WebDriver dr;
	public Reusablemethod(WebDriver dr) 
	{
		this.dr=dr;
	}

	
	By orders=By.xpath("//button[@routerlink='/dashboard/myorders']");
	
	public void elementtoappear(By method)
	{
		  System.out.println("Wait method calling ");
	      WebDriverWait w= new WebDriverWait(dr, Duration.ofSeconds(100));
	      w.until(ExpectedConditions.visibilityOfElementLocated(method));
	}
	
	public void orderdetails()
	{
		dr.findElement(orders).click();
	}
	
}
