 package EndtoEnd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Productselection extends Reusablemethod

{
	WebDriver dr;
   public Productselection(WebDriver dr)
   {
	   super(dr);
	   this.dr=dr;
   }
	
	
	By product=By.cssSelector(".mb-3");
	By productname= By.cssSelector("b");
	By AddtoCart=By.cssSelector(".card-body button:last-of-type");
	By textmessage=By.xpath("//div[@role='alertdialog']");
	By cartcheck=By.xpath("//button[@routerlink='/dashboard/cart']");
	By wait =By.id("toast-container");
	
	
	public String getproductlist(String name1)
	{
     List<WebElement>s =dr.findElements(product);
     System.out.println("No of Products:"+ s.size()); 
     String pname=null;
     for (WebElement e:s)
     {
   	  pname=e.findElement(productname).getText();
   	  System.out.println("Product name : "+pname);
   	  if(pname.equalsIgnoreCase(name1))
   	  {
   		  e.findElement(AddtoCart).click();
   		  //e.findElement(By.xpath("//div[@class='card']/div/button[2]")).click(); [xpath not working]
   		  break;
   	  }
   	
     }
     return pname;
	 
	}
	public void gettext()
	{
		
	    elementtoappear(wait);
		System.out.println("Message displayed :"+dr.findElement(textmessage).getText());
	}
	
	public void getcartclick()
	{
	    dr.findElement(cartcheck).click();
	}
}
