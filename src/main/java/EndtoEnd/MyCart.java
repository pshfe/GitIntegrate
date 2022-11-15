package EndtoEnd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MyCart
{
	WebDriver dr;
	public MyCart(WebDriver dr) 
	{
		this.dr=dr;
	}

	By cartitems=By.cssSelector(".cartSection h3");
	By checkout =By.cssSelector(".totalRow button");
	
	public List<WebElement> itemscheck()
	{
		return dr.findElements(cartitems);
	}
	
	public void productcheck(String name)
	{
		List<WebElement> cartproducts=itemscheck();
		for (WebElement a:cartproducts)
	      {
	    	  if(a.getText().equalsIgnoreCase(name))
	    	  {
	    		 
	    		  System.out.println("Proper product added");
	    	  }
	      }
	}
	
	public void getcheckout()
	{
		dr.findElement(checkout).click();
	}
	
	
}
